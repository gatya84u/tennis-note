package u.akita.tennis_note.ui.checklist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import u.akita.tennis_note.R
import u.akita.tennis_note.databinding.FragmentChecklistBinding
import u.akita.tennis_note.databinding.ItemChecklistBinding
import u.akita.tennis_note.enum.CheckCategory
import u.akita.tennis_note.repository.model.Checklist
import u.akita.tennis_note.ui.dialog.CheckItem

class ChecklistFragment: Fragment() {
    private var _binding: FragmentChecklistBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ChecklistViewModel

    override fun onAttach(context: Context){
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChecklistBinding.inflate(inflater, container, false)
        viewModel = ChecklistViewModel(requireActivity().application)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val completeContainer = binding.completeListContainer
        val challengingContainer = binding.challengingListContainer

        // LiveData を監視してデータが変更されたときに UI を更新する
        viewModel.getListData().observe(viewLifecycleOwner) { allChecklist ->
            Log.i("allChecklist", allChecklist.toString())
            updateUI(allChecklist)
        }

        //取組中課題の開閉
        binding.challengingTasks.setOnClickListener{
            if(challengingContainer.visibility == View.GONE){
                challengingContainer.visibility = View.VISIBLE
            }else{
                challengingContainer.visibility = View.GONE
            }
        }

        //クリア済み課題の開閉
        binding.completeTasks.setOnClickListener{
            if(completeContainer.visibility == View.GONE){
                completeContainer.visibility = View.VISIBLE
            }else{
                completeContainer.visibility = View.GONE
            }
        }

        binding.registerNewTask.setOnClickListener{
            val dialog = CheckItem()
            dialog.show(parentFragmentManager, "CheckItemDialog")
        }
    }

    private fun updateUI(checklist: List<Checklist>){
        val challengingList = checklist.filter { !it.completeFlag }
        val completeList = checklist.filter { it.completeFlag }

        // 挑戦中リストを更新
        updateListContainer(binding.challengingListContainer, challengingList)

        // 完了リストを更新
        updateListContainer(binding.completeListContainer, completeList)
    }

    private fun updateListContainer(container: ViewGroup, checklist: List<Checklist>){
        container.removeAllViews()
        val inflater = LayoutInflater.from(requireContext())

        for(item in checklist){
            val binding = DataBindingUtil.inflate<ItemChecklistBinding>(
                inflater, R.layout.item_checklist, container, false
            )

            val categoryStr = CheckCategory.values().find { it.value == item.themeCategory.toString() }
            binding.themeCategory.text = categoryStr?.displayValue ?: "不明なカテゴリ"
            binding.checkBox.isChecked = item.completeFlag
            binding.themeTitle.text = item.themeTitle

            container.addView(binding.root)
        }
    }
}
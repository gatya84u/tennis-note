package u.akita.tennis_note.ui.checklist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import u.akita.tennis_note.databinding.FragmentChecklistBinding
import u.akita.tennis_note.ui.dialog.CheckItem

class ChecklistFragment: Fragment() {
    private var _binding: FragmentChecklistBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context){
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChecklistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val completeContainer = binding.completeListContainer

        binding.completeTasks.setOnClickListener{
            if(completeContainer.visibility == View.GONE){
                completeContainer.visibility = View.VISIBLE
            }else{
                completeContainer.visibility = View.GONE
            }
        }

        binding.registerNewTask.setOnClickListener{
            val dialog = CheckItem(requireContext())
            dialog.show()
        }
    }
}
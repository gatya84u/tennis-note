package u.akita.tennis_note.ui.note_list

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import u.akita.tennis_note.R
import u.akita.tennis_note.databinding.FragmentNoteListBinding
import u.akita.tennis_note.databinding.ItemChecklistBinding
import u.akita.tennis_note.databinding.ItemNoteListBinding
import u.akita.tennis_note.enum.MatchType
import u.akita.tennis_note.enum.SearchMatchType
import u.akita.tennis_note.enum.SearchPeriod
import u.akita.tennis_note.repository.dataclass.MatchWithDate
import u.akita.tennis_note.ui.dialog.DatePick
import u.akita.tennis_note.ui.dialog.DateSelectedListener
import u.akita.tennis_note.ui.note.NoteFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteListFragment: Fragment(), DateSelectedListener {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteListViewModel

    override fun onAttach(context: Context){
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        viewModel = NoteListViewModel(requireActivity().application)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filterContainer = binding.filterContainer

        //絞り込み条件タップ時に絞り込み条件を開閉する
        binding.searchCondition.setOnClickListener{
            if(filterContainer.visibility == View.GONE){
                filterContainer.visibility = View.VISIBLE
            }else{
                filterContainer.visibility = View.GONE
            }
        }
        //絞り込み条件の全期間が選択されたら開始日・期間を非表示にする
        binding.allPeriod.setOnClickListener {
            binding.periodCondition.visibility = View.GONE
        }

        //絞り込み条件の指定期間が選択されたら開始日・期間を非表示にする
        binding.pointedPeriod.setOnClickListener {
            binding.periodCondition.visibility = View.VISIBLE
        }
        //開始日の初期値をセット
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        binding.periodStartDate.setText(currentDate)

        //開始日をタップしたらカレンダーを表示
        binding.periodStartDate.setOnTouchListener{ v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                val dateFragment = DatePick(this@NoteListFragment)
                dateFragment.show(parentFragmentManager, "datePicker")
                true
            }else{
                false
            }
        }

        //検索期間のpicker作成
        val periodAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            SearchPeriod.values().map{it.displayValue}
        )

        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.specifiedPeriod.adapter = periodAdapter
        binding.specifiedPeriod.setSelection(1)

        //試合種別のpicker作成
        val matchTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            SearchMatchType.values().map{it.displayValue}
        )
        matchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.matchType.adapter = matchTypeAdapter
        binding.matchType.setSelection(0)

        binding.newRegistration.setOnClickListener{
            val fragment = NoteFragment().apply {
                arguments = Bundle().apply {
                    putString("matchDateId", "0")
                    putString("matchDate", currentDate)
                    putString("matchId", "0")
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .addToBackStack(null)
                .commit()
        }

        // LiveDataを監視してデータが変更されたときにUIを更新する
        viewModel.getListData().observe(viewLifecycleOwner) { allMatches ->
            Log.i("allNotes", allMatches.toString())
            updateUI(allMatches)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateUI(allMatches: List<MatchWithDate>?) {
        binding.matchListContainer.removeAllViews()
        val inflater = LayoutInflater.from(requireContext())

        for(match in allMatches!!){
            val itemBinding = DataBindingUtil.inflate<ItemNoteListBinding>(
                inflater, R.layout.item_note_list, binding.matchListContainer, false
            )
            val matchTypeStr = MatchType.values().find { it.value == match.matchType.toString() }
            itemBinding.matchDate.text = match.matchDate
            itemBinding.matchScore.text = match.matchScore
            itemBinding.opponent.text = match.opponent
            itemBinding.matchType.text = matchTypeStr?.displayValue ?: "不明な試合種別"
            itemBinding.matchId.text = match.matchId
            itemBinding.matchDateId.text = match.matchDateId

            binding.matchListContainer.addView(itemBinding.root)
        }
    }


    override fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = "$year/${month.plus(1)}/$day"
        binding.periodStartDate.setText(selectedDate)

    }




}
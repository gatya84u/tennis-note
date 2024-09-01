package u.akita.tennis_note.ui.note_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import u.akita.tennis_note.databinding.FragmentNoteListBinding
import u.akita.tennis_note.enum.MatchType
import u.akita.tennis_note.enum.SearchMatchType
import u.akita.tennis_note.enum.SearchPeriod
import u.akita.tennis_note.ui.dialog.DatePick
import u.akita.tennis_note.ui.dialog.DateSelectedListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteListFragment: Fragment(), DateSelectedListener {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context){
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
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
        //TODO setOnTouchListenerにした方が反応良くなる？
        binding.periodStartDate.setOnClickListener{
            this.showDatePicker()
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
//            TODO NoteFragmentに値を渡す
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showDatePicker() {
        val datePick = DatePick(this)
        datePick.show(parentFragmentManager, "datePicker")
    }

    override fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = "$year/${month.plus(1)}/$day"
        binding.periodStartDate.setText(selectedDate)

    }


}
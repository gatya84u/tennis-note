package u.akita.tennis_note.ui.note

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import u.akita.tennis_note.databinding.FragmentNoteBinding
import u.akita.tennis_note.enum.MatchType
import u.akita.tennis_note.ui.dialog.DatePick
import u.akita.tennis_note.ui.dialog.DateSelectedListener

class NoteFragment : Fragment(), DateSelectedListener {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = NoteViewModel(requireActivity().application)
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val initMatchDateId = arguments?.getString("matchDateId")
        if (initMatchDateId != null) {
            binding.matchDateId.text = initMatchDateId

            if(initMatchDateId != "0"){
                //TODO initMatchDateIdが0ではない場合、DBからデータを取得して初期値としてセット
            }else{
                val initMatchDate: Editable = Editable.Factory.getInstance().newEditable(arguments?.getString("matchDate"))
                binding.matchDate.text = initMatchDate
            }
        }

        binding.registerButton.setOnClickListener{
            viewModel.registerMatchData(binding)
        }

        binding.matchDate.setOnTouchListener{ v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                val dateFragment = DatePick(this@NoteFragment)
                dateFragment.show(parentFragmentManager, "datePicker")
                true
            }else{
                false
            }
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            MatchType.values().map{it.displayValue}
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.matchType.adapter = adapter
        binding.matchType.setSelection(0)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showDatePicker() {
        val datePick = DatePick(this)
        datePick.show(parentFragmentManager, "datePicker")
    }

    override fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = "$year/${month.plus(1)}/$day"
        binding.matchDate.setText(selectedDate)
    }
}

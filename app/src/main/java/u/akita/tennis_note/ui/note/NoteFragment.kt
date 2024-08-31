package u.akita.tennis_note.ui.note

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import u.akita.tennis_note.databinding.FragmentNoteBinding
import u.akita.tennis_note.enum.MatchTypeSpinner
import u.akita.tennis_note.ui.dialog.DatePick

class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
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
        binding = FragmentNoteBinding.inflate(inflater, container, false)
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
            MatchTypeSpinner.values().map{it.displayValue}
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.matchType.adapter = adapter
        binding.matchType.setSelection(0)

        return binding.root
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = "$year/${month.plus(1)}/$day"
        binding.matchDate.setText(selectedDate)
    }
}

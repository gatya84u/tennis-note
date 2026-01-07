package u.akita.tennis_note.ui.note

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import u.akita.tennis_note.R
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
        val initMatchId = arguments?.getString("matchId")
        val navController = findNavController()
        binding.matchDateId.text = initMatchDateId
        binding.matchId.text = initMatchId

        //spinnerの設定
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            MatchType.values().map{it.displayValue}
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        binding.matchType.adapter = adapter
        binding.matchType.setSelection(0)

        if(initMatchDateId == "0"){
            val initMatchDate: Editable = Editable.Factory.getInstance().newEditable(arguments?.getString("matchDate"))
            binding.matchDate.text = initMatchDate
        }else{
            lifecycleScope.launch {
                val matchDetailInfo = viewModel.getMatchDetailData(initMatchId!!)
                binding.matchDate.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.matchDate)
                binding.opponent.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.opponent)
                binding.scoreSelf.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.gainGameCount.toString())
                binding.scoreOpponent.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.lostGameCount.toString())
                binding.lookingBackGame.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.matchNote)
                binding.matchName.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.matchName)
//                binding.movieUrl.text = Editable.Factory.getInstance().newEditable(matchDetailInfo.movieUrl)

                val matchType = MatchType.values().find { it.value == matchDetailInfo.matchType }
                binding.matchType.setSelection(MatchType.values().indexOf(matchType))
            }
        }

        binding.registerButton.setOnClickListener{
            lifecycleScope.launch {
                try{
                    viewModel.registerMatchData(binding)
                    navController.navigate(R.id.transition_to_note_list)
                }catch (e: Exception){
                    Log.e("NoteFragment", "Error", e)
                }
            }
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


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = "$year/${month.plus(1)}/$day"
        binding.matchDate.setText(selectedDate)
    }
}

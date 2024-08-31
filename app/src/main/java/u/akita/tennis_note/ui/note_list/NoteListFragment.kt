package u.akita.tennis_note.ui.note_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import u.akita.tennis_note.databinding.FragmentNoteListBinding

class NoteListFragment: Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    override fun onAttach(context: Context){
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        val searchCondition = binding.searchCondition
        val filterContainer = binding.filterContainer
        val newRegistration = binding.newRegistration

        searchCondition.setOnClickListener{
            if(filterContainer.visibility == View.GONE){
                filterContainer.visibility = View.VISIBLE
            }else{
                filterContainer.visibility = View.GONE
            }
        }

        newRegistration.setOnClickListener{
//            TODO NoteFragmentに値を渡す
        }
        return binding.root
    }
}
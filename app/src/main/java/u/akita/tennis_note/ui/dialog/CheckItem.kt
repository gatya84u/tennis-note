package u.akita.tennis_note.ui.dialog

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import u.akita.tennis_note.databinding.DialogCheckItemBinding
import u.akita.tennis_note.enum.CheckCategory
import u.akita.tennis_note.ui.checklist.ChecklistViewModel

class CheckItem(context: Context): AlertDialog(context) {
    private var _binding: DialogCheckItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ChecklistViewModel

    init {
        _binding = DialogCheckItemBinding.inflate(LayoutInflater.from(context))
        setView(binding.root)
        setupSpinner()

        binding.registerTask.setOnClickListener{
            //TODO DB登録のメソッドを呼び出す
        }
    }

    private fun setupSpinner(){
        val adapter = ArrayAdapter(
            context,
            R.layout.simple_spinner_item,
            CheckCategory.values().map{it.displayValue}
        )

        adapter.setDropDownViewResource(R.layout.simple_spinner_item)
        binding.checkCategory.adapter = adapter
        binding.checkCategory.setSelection(0)
    }
}
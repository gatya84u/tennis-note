package u.akita.tennis_note.ui.dialog

import android.R
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import u.akita.tennis_note.databinding.DialogCheckItemBinding
import u.akita.tennis_note.enum.CheckCategory
import u.akita.tennis_note.ui.checklist.ChecklistViewModel

class CheckItem: DialogFragment() {
    private var _binding: DialogCheckItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ChecklistViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCheckItemBinding.inflate(LayoutInflater.from(context))
        viewModel = ViewModelProvider(this).get(ChecklistViewModel::class.java)
        setupSpinner()

        binding.registerTask.setOnClickListener{
            lifecycleScope.launch {
                try{
                    viewModel.registerNewCheckItem(binding)
                    dismiss()
                }catch (e: Exception){
                    Log.e("CheckItem", "Error", e)
                }
            }
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle("Register Task")
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
    }

    private fun setupSpinner(){
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            CheckCategory.values().map{it.displayValue}
        )

        adapter.setDropDownViewResource(R.layout.simple_spinner_item)
        binding.checkCategory.adapter = adapter
        binding.checkCategory.setSelection(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
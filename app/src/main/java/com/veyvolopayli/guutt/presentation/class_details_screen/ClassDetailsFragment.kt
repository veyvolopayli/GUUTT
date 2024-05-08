package com.veyvolopayli.guutt.presentation.class_details_screen

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentClassDetailsBinding
import com.veyvolopayli.guutt.domain.model.ClassObject
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ClassDetailsFragment : BottomSheetDialogFragment(R.layout.fragment_class_details) {
    private var binding: FragmentClassDetailsBinding? = null
    private val vm: ClassDetailsViewModel by viewModels()

    private var classObject: ClassObject? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentClassDetailsBinding.bind(view)
        this.binding = binding

        classObject = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(CLASS_OBJECT_KEY, ClassObject::class.java)
        } else {
            arguments?.getSerializable(CLASS_OBJECT_KEY) as? ClassObject
        } ?: throw IllegalArgumentException()

        binding.classTitle.text = classObject!!.title
        binding.department.text = classObject!!.description.department
        binding.building.text = classObject!!.description.building

        vm.noteState.observe(viewLifecycleOwner) {
            binding.noteEditText.setText(it.content)
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm")
            binding.createdAtTv.text = it.createdAt.format(formatter)
        }
    }

    companion object {
        private const val CLASS_OBJECT_KEY = "class_object_key"
        fun newInstance(classObject: ClassObject): ClassDetailsFragment {
            val classDetailsFragment = ClassDetailsFragment().also {
                it.arguments = bundleOf(CLASS_OBJECT_KEY to classObject)
            }
            return classDetailsFragment
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onPause() {
        super.onPause()
        val noteContent = binding?.noteEditText?.text?.toString()
        if (noteContent.isNullOrEmpty()) return
        vm.saveNote(classObject!!, noteContent)
    }

    override fun onResume() {
        super.onResume()
        if (binding != null) vm.getNote(classObject!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
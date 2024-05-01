package com.veyvolopayli.guutt.presentation.choose_group_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.FragmentChooseGroupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseGroupFragment : Fragment(R.layout.fragment_choose_group) {
    private var binding: FragmentChooseGroupBinding? = null
    private val viewModel: ChooseGroupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentChooseGroupBinding.bind(view)
        this.binding = binding

        viewModel.groupsState.observe(viewLifecycleOwner) { groups ->
            val groupsAdapter = GroupsRvAdapter()
            groupsAdapter.addGroups(groups)
            groupsAdapter.onClick = { group ->
                viewModel.setSelectedGroup(group)
                binding.continueButton.isActive = true
            }

            binding.groupsRv.apply {
                adapter = groupsAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.toastMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.continueButton.setOnClickListener {
            val group = viewModel.selectedGroup.value
            if (group != null) {
                viewModel.saveGroup(group)
                val bundle = bundleOf("group" to group)
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.chooseGroupFragment, true).build()
                findNavController().navigate(R.id.action_chooseGroupFragment_to_mainFragment, bundle, navOptions)
            } else {
                Toast.makeText(requireContext(), "Group is null", Toast.LENGTH_SHORT).show()
            }
        }

        binding.noGroupTv.setOnClickListener {
            findNavController().navigate(R.id.action_chooseGroupFragment_to_signInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
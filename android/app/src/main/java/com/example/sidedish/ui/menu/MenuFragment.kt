package com.example.sidedish.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sidedish.R
import com.example.sidedish.databinding.FragmentMenuBinding
import com.example.sidedish.model.MenuListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModels()
    private val adapter = MenuAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        viewModel.loadMenuList()
    }

    private fun setupViews() {
        binding.rvMenuListView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.menu.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            val context = context ?: return@observe
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onItemClicked(menu: MenuListItem.Menu) {
        val bundle = bundleOf("key" to menu.id)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}
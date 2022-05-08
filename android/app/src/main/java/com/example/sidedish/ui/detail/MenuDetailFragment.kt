package com.example.sidedish.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sidedish.R
import com.example.sidedish.databinding.FragmentDetailBinding
import com.example.sidedish.ui.animation.ZoomOutPageTransformer
import com.example.sidedish.ui.viewmodel.MenuDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDetailFragment : Fragment() {

    private val key: Int by lazy { requireNotNull(arguments?.getInt("key")) }
    private val viewModel: MenuDetailViewModel by viewModels()
    private val adapter = ImageViewPagerAdapter()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_detailFragment_to_menuFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupViews()
        setupObservers()

        viewModel.loadMenuDetail(key)
    }

    private fun setupViews() {
        with(binding) {
            pagerDetailImage.adapter = adapter
            pagerDetailImage.setPageTransformer(ZoomOutPageTransformer())
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            menuDetail.observe(viewLifecycleOwner) {
                adapter.replaceAll(mutableListOf(it.imageUrl))
            }
            menuOrderCompleteEvent.observe(viewLifecycleOwner) {
                val orderCompleteDialog = OrderCompleteDialogFragment.newInstance()
                orderCompleteDialog.show(parentFragmentManager, "OrderCompleteDialogFragment")
            }
            error.observe(viewLifecycleOwner) {
                val context = context ?: return@observe
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
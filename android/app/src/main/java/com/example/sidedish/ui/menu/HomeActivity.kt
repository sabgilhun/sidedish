package com.example.sidedish.ui.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sidedish.R
import com.example.sidedish.databinding.ActivityHomeBinding
import com.example.sidedish.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.jwtLoadCompleteEvent.observe(this) {
            findNavController(R.id.loginFragment)
                .navigate(R.id.action_loginFragment_to_menuFragment)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code") ?: return
        viewModel.loadJwt(code)
    }
}
package com.contus.pullrequests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.contus.pullrequests.databinding.ActivityMainBinding
import com.contus.pullrequests.ui.viewmodel.PullRequestsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        PullRequestsAdapter()
    }

    private val viewModel: PullRequestsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGrocery.adapter = adapter

        viewModel.getViewState().observe(this) {
            it?.let {
                renderViewState()
            }
        }
    }
}
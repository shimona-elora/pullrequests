package com.contus.pullrequests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.contus.pullrequests.data.model.viewstate.PullRequestsViewState
import com.contus.pullrequests.databinding.ActivityMainBinding
import com.contus.pullrequests.ui.viewmodel.PullRequestsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        PullRequestsAdapter()
    }

    private val viewModel: PullRequestsViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGrocery.adapter = adapter

        viewModel.getViewState().observe(this) {
            it?.let {
                renderViewState(it)
            }
        }

        viewModel.getPullRequests()
    }

    private fun renderViewState(viewState: PullRequestsViewState) {
        if (viewState.loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }

        if (viewState.loading || !viewState.error.isNullOrEmpty()) {
            binding.vBackground.visibility = View.VISIBLE
        } else {
            binding.vBackground.visibility = View.GONE
        }

        if (!viewState.error.isNullOrEmpty()) {
            binding.tvErrorMessage.visibility = View.VISIBLE
            binding.tvErrorMessage.text = viewState.error
        } else {
            binding.tvErrorMessage.visibility = View.GONE
        }

        adapter.submitList(viewState.list)
    }
}
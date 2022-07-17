package com.contus.pullrequests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.contus.imageloading.databinding.ActivityMainBinding
import com.contus.pullrequests.ui.viewmodel.PullRequestsViewModel

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        PullRequestsAdapter()
    }

    private val viewModel: PullRequestsViewModel by lazy {
        ViewModelProvider(this).get(PullRequestsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGrocery.adapter = adapter

        groceryViewModel.liveData.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }

        groceryViewModel.getGroceryList(applicationContext)

    }
}
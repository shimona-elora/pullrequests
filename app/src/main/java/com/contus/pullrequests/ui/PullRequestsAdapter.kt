package com.contus.pullrequests.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contus.pullrequests.R
import com.contus.pullrequests.data.model.response.PullRequest
import com.contus.pullrequests.databinding.ItemPullRequestsBinding

class PullRequestsAdapter :
    ListAdapter<PullRequest, PullRequestsAdapter.ViewHolder>(AdapterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPullRequestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: ItemPullRequestsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: PullRequest) {
            if (!data.user?.userImage.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(data.user?.userImage)
                    .circleCrop()
                    .into(binding.ivAvatar)
                binding.ivAvatar.visibility = View.VISIBLE
            } else {
                binding.ivAvatar.visibility = View.GONE
            }

            binding.tvTitle.text = data.title

            binding.tvCreated.text = itemView.context.getString(R.string.created_at, data.createdAt)

            binding.tvClosed.text = itemView.context.getString(R.string.closed_at, data.closedAt)

            binding.tvName.text = data.user?.userName
        }
    }

    object AdapterDiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem == newItem
        }
    }
}
package com.jquiroga.imdumb.features.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jquiroga.imdumb.R
import com.jquiroga.imdumb.databinding.ItemActorBinding
import com.jquiroga.imdumb.features.detail.model.ActorUiModel

class ActorAdapter : ListAdapter<ActorUiModel, ActorAdapter.ActorViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ActorViewHolder(
        private val binding: ItemActorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: ActorUiModel) {
            binding.textActorName.text = actor.name
            binding.textCharacter.text = actor.character
            Glide.with(binding.imageProfile.context)
                .load(actor.profileUrl)
                .placeholder(R.drawable.ic_person_placeholder)
                .centerCrop()
                .into(binding.imageProfile)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ActorUiModel>() {
            override fun areItemsTheSame(oldItem: ActorUiModel, newItem: ActorUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ActorUiModel, newItem: ActorUiModel): Boolean =
                oldItem == newItem
        }
    }
}

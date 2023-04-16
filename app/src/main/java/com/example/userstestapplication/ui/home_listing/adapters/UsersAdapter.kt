package com.example.userstestapplication.ui.home_listing.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userstestapplication.R
import com.example.userstestapplication.databinding.UserItemBinding
import com.example.userstestapplication.model.UsersData

class UsersAdapter(private val clickListener: (UsersData) -> Unit = {}): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var usersList = arrayListOf<UsersData>()

    inner class UsersViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root)

    fun refreshList(list: ArrayList<UsersData>){
        usersList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.binding.apply {
            with(usersList[position]){
                root.setOnClickListener {
                    clickListener(this)
                }
                name.text = this.first_name.plus(" ").plus(this.last_name)
                emailId.text = this.email
                Glide.with(holder.itemView.context)
                    .load(this.avatar)
                    .placeholder(R.drawable.default_image_placeholder)
                    .error(R.drawable.default_image_placeholder)
                    .into(userPic)
            }
        }
    }

    override fun getItemCount() = usersList.size

}
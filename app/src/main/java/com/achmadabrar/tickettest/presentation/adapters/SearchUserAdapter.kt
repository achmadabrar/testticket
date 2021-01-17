package com.achmadabrar.tickettest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.tickettest.R
import com.achmadabrar.tickettest.data.models.Item
import com.achmadabrar.tickettest.presentation.viewholders.ListUserViewHolder


class SearchUserAdapter(
    val list: List<Item>?
): RecyclerView.Adapter<ListUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        list?.get(position).let {
            holder.bind(it)
        }

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}
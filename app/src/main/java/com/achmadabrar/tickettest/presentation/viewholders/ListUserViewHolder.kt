package com.achmadabrar.tickettest.presentation.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.tickettest.data.models.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user.view.*

class ListUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(userFav: Item?) {
        with(itemView) {

            if (userFav != null) {
                text_view_username.text = userFav.login
                Glide.with(this)
                    .load(userFav.avatar_url)
                    .centerInside()
                    .apply(RequestOptions().override(250, 250))
                    .into(image_view_profile)
            }
        }
    }
}
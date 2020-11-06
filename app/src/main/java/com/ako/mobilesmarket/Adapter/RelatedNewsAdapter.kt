package com.ako.mobilesmarket.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ako.mobilesmarket.R
import com.ako.mobilesmarket.Related.related
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.productlist.view.*
import org.jetbrains.anko.toast

class RelatedNewsAdapter(val context: Context,val related: related,val show :relatednews):RecyclerView.Adapter<RelatedNewsAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedNewsAdapter.Holder {
        val item=LayoutInflater.from(context).inflate(R.layout.productlist,parent,false)
        return Holder(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RelatedNewsAdapter.Holder, position: Int) {
        holder.itemView.phoneName.text=related.relatedproducts[position].name
        holder.itemView.phonePrice.text= "$" + related.relatedproducts[position].price.toString()
        Picasso.get().load("http://128.199.112.232:4500/"+ related.relatedproducts[position].image).into(holder.itemView.phoneImage)
        holder.itemView.phoneFavourate.setOnClickListener {
            if(FirebaseAuth.getInstance().currentUser==null){
                context.toast("Please Login or sign up to continue")
            }else{
                holder.itemView.phoneselectedFavourate.visibility=View.VISIBLE
                context.toast("Add to White List successful")
            }
        }
        holder.itemView.phoneselectedFavourate.setOnClickListener {
            holder.itemView.phoneselectedFavourate.visibility=View.GONE
            context.toast("Remove from White List successful")
        }
        holder.itemView.showdetail.setOnClickListener {
            show.relatednews(related.relatedproducts[position]._id)
        }
    }

    override fun getItemCount(): Int {
      return related.relatedproducts.size
    }
    class Holder(item : View):RecyclerView.ViewHolder(item)
}
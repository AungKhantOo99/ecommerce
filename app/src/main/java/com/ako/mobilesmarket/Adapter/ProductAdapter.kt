package com.ako.mobilesmarket.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ako.mobilesmarket.ApiDataClass.productItem
import com.ako.mobilesmarket.MainActivityViewModel
import com.ako.mobilesmarket.PhoneDetail
import com.ako.mobilesmarket.R
import com.ako.mobilesmarket.RoomDataBase.UserEntity
import com.ako.mobilesmarket.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.productlist.view.*
import org.jetbrains.anko.toast

@Suppress("DEPRECATION")
class ProductAdapter(val context: Context, val phone :List<productItem>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ViewHloder(item:View): RecyclerView.ViewHolder(item)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val layout=LayoutInflater.from(context).inflate(R.layout.productlist,parent,false)
        return ViewHloder(layout)
    }
    override fun getItemCount(): Int {
        return phone.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val post=phone[position]
         holder.itemView.phoneName.text=phone[position].name
         holder.itemView.phonePrice.text="$"+phone[position].price.toString()
         Picasso.get().load("http://128.199.112.232:4500/"+ phone[position].image).into(holder.itemView.phoneImage)
        holder.itemView.phoneFavourate.setOnClickListener {
            if(getInstance().currentUser==null){
                context.toast("Please Login or sign up to continue")
            }else{
                val user = UserEntity(0, getInstance().uid.toString(),phone[position].name,
                    phone[position].price,"http://128.199.112.232:4500/"+ phone[position].image)
               viewModel.insertUserInfo(user)
                holder.itemView.phoneselectedFavourate.visibility=View.VISIBLE
                context.toast("Add to White List successful")
            }
        }
        holder.itemView.phoneselectedFavourate.setOnClickListener {
            holder.itemView.phoneselectedFavourate.visibility=View.GONE
            context.toast("Remove from White List successful")
        }
        holder.itemView.showdetail.setOnClickListener {
            val intent= Intent(context,PhoneDetail::class.java)
            intent.putExtra("key",post._id)
            context.startActivity(intent)
        }
    }

}
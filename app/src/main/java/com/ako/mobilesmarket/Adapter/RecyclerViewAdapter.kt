package com.ako.mobilesmarket.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ako.mobilesmarket.R
import com.ako.mobilesmarket.RoomDataBase.UserEntity
import com.ako.mobilesmarket.viewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.whitelist.view.*
import org.jetbrains.anko.toast

class RecyclerViewAdapter(val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items= ArrayList<UserEntity>()
    fun setListDate(data :ArrayList<UserEntity>){
        this.items=data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val item= LayoutInflater.from(parent.context).inflate(R.layout.whitelist,parent,false)
        return MyViewHolder(item)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
           holder.itemView.favname.text= items[position].bname
          holder.itemView.favprice.text= items[position].price.toString()
        Picasso.get().load(items[position].img).into(holder.itemView.favimg)
        holder.itemView.delete.setOnClickListener {
            viewModel.deleteUserInfo(items.get(position))
        }
    }
    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        val name=view.favname
        val price=view.favprice
        val image=view.favimg
        fun bind(data : UserEntity){
            name.text=data.bname
            price.text=data.price.toString()

        }
    }

}
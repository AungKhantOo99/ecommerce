package com.ako.mobilesmarket.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ako.mobilesmarket.Adapter.ProductAdapter
import com.ako.mobilesmarket.ApiDataClass.productItem
import com.ako.mobilesmarket.MainActivityViewModel
import com.ako.mobilesmarket.R
import com.ako.mobilesmarket.RoomDataBase.UserEntity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val all=root.findViewById<RecyclerView>(R.id.recyclerall)
        val url="http://128.199.112.232:4500/api/products"
        doAsync {
            val apilink= URL(url).readText()
            uiThread {

                refleshnews.setOnRefreshListener {
                    refleshnews.isRefreshing=false
                    doAsync {
                        val apilink= URL(url).readText()
                        uiThread {
                            val product = Gson().fromJson(apilink,Array<productItem>::class.java).toList()
                            all.layoutManager= GridLayoutManager(context,2)
                            all.adapter= context?.let { it1 -> ProductAdapter(it1,product) }
                            loading.visibility=View.GONE
                        }
                    }
                }
                val product = Gson().fromJson(apilink,Array<productItem>::class.java).toList()
                all.layoutManager= GridLayoutManager(context,2)
                all.adapter= context?.let { it1 -> ProductAdapter(it1,product) }
                loading.visibility=View.GONE
            }
        }
        return root
    }

}
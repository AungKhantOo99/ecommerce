package com.ako.mobilesmarket.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.ako.mobilesmarket.Adapter.RecyclerViewAdapter
import com.ako.mobilesmarket.MainActivityViewModel
import com.ako.mobilesmarket.R
import com.ako.mobilesmarket.RoomDataBase.UserEntity
import com.ako.mobilesmarket.viewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_whitelist.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.jvm.java as java1


lateinit var recyclerViewAdapter: RecyclerViewAdapter
class WhiteListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_whitelist, container, false)
        doAsync {
            uiThread {
                recyclerwhitelist.apply {
                    layoutManager= LinearLayoutManager(context)
                    recyclerViewAdapter=RecyclerViewAdapter(context)
                    adapter=recyclerViewAdapter
                    val div= DividerItemDecoration(context, OrientationHelper.VERTICAL)
                    addItemDecoration(div)
                }
            //    viewModel=ViewModelProviders.of(this@WhiteListFragment).get(MainActivityViewModel::class.java1)
                val abc= UserEntity(0, "","",0.0,"abc")
                viewModel.insertUserInfo(abc)
                viewModel.deleteUserInfo(abc)
                viewModel.getAllUsersObservers().observe(viewLifecycleOwner, Observer {
                    Log.d("Hellokotlin",it.toString())
                    recyclerViewAdapter.setListDate(ArrayList(it))
                    recyclerViewAdapter.notifyDataSetChanged()
                })
            }
        }
        return root
    }
}
package com.ako.mobilesmarket

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ako.mobilesmarket.Adapter.ProductAdapter
import com.ako.mobilesmarket.Adapter.RelatedNewsAdapter
import com.ako.mobilesmarket.Adapter.relatednews
import com.ako.mobilesmarket.ApiDataClass.productItem
import com.ako.mobilesmarket.Related.related
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_phone_detail.*
import okhttp3.*
import org.jetbrains.anko.custom.onUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.net.URL

class PhoneDetail() : AppCompatActivity(),relatednews {
    lateinit var  url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_detail)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val id= intent.getStringExtra("key")
        fetch(id)
    }
    private fun fetch(abc:String){
        val url="http://128.199.112.232:4500/api/products/$abc"
        val req= Request.Builder().url(url).build()
        val client= OkHttpClient()
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }
            override fun onResponse(call: Call, response: Response) {
                val body= response.body?.string()
                println(body)
                val json= GsonBuilder().create()
                val home=json.fromJson(body,related::class.java)
                Log.d("ItemDetailActivity",home.toString())
                runOnUiThread {
                    BrandName.text= home.product.name
                    Prices.text="$"+ home.product.price.toString()
                    Picasso.get().load("http://128.199.112.232:4500/"+home.product.image).into(phonePhoto)
                    phoneSpecification.text=
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml((home.product.specification),
                            Html.FROM_HTML_MODE_COMPACT)
                        else Html.fromHtml(home.product.specification)
                    recyclerrelated.layoutManager= GridLayoutManager(this@PhoneDetail,2)
                    recyclerrelated.adapter=RelatedNewsAdapter(this@PhoneDetail,home,this@PhoneDetail)
                }
            }
        })
    }

    override fun relatednews(getid: String) {
       fetch(getid)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
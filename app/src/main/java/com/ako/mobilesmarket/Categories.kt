package com.ako.mobilesmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.ako.mobilesmarket.Adapter.CategoriesAdapter
import com.ako.mobilesmarket.CategoriesDataClass.categoriesItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_categories.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class Categories : AppCompatActivity() {
    lateinit var getdata:String
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        getdata=intent.getStringExtra("keys")
        supportActionBar!!.title=getdata
        when(getdata){
            "Apple"-> url="http://128.199.112.232:4500/api/categories/apple"
            "Huawei"->url="http://128.199.112.232:4500/api/categories/huawei"
            "Samsung"->url="http://128.199.112.232:4500/api/categories/samsung"
            "Xiaomi"->url="http://128.199.112.232:4500/api/categories/huawei"
            "Oppo"->url="http://128.199.112.232:4500/api/categories/apple"
            "Vivo"->url="http://128.199.112.232:4500/api/categories/huawei"
            "Google"->url="http://128.199.112.232:4500/api/categories/huawei"
        }
        doAsync {
            val apilink= URL(url).readText()
            uiThread {
                Log.d("product",url)
                val product = Gson().fromJson(apilink,Array<categoriesItem>::class.java).toList()
                recycler_product.layoutManager= GridLayoutManager(this@Categories,2)
                recycler_product.adapter= CategoriesAdapter(this@Categories,product)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}



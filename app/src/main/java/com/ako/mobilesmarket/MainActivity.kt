package com.ako.mobilesmarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ako.mobilesmarket.Adapter.ProductAdapter
import com.ako.mobilesmarket.ApiDataClass.product
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL
lateinit var viewModel: MainActivityViewModel
class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        viewModel= ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        Log.d("Hello","Hello")
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        drawerLayout = findViewById(R.id.container)
        nav_view.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.apple-> showCategories("Apple")
                    R.id.huawei-> showCategories("Huawei")
                    R.id.samsung-> showCategories("Samsung")
                    R.id.Xiaomi-> showCategories("Xiaomi")
                    R.id.Oppo-> showCategories("Oppo")
                    R.id.Vivo-> showCategories("Vivo")
                    R.id.Google-> showCategories("Google")
                }
                return true
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings){
            if(drawerLayout.isDrawerOpen(Gravity.RIGHT))
            {
                drawerLayout.closeDrawer(Gravity.RIGHT)
            }
            else{
                drawerLayout.openDrawer(Gravity.RIGHT)
            }
        }
        else
            if (item.itemId==R.id.action_cart){
                toast("Click Cart")
            }
        return super.onOptionsItemSelected(item)
    }
    fun showCategories(key:String){
        val intent=Intent(this,Categories::class.java)
        intent.putExtra("keys",key)
        startActivity(intent)
    }

}
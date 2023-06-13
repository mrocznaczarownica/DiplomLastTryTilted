package com.example.diplomlasttrytilted.userActivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.auxiliaryClasses.ProductAdapter
import com.example.diplomlasttrytilted.dataBase.DBHelper

class CatalogActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: TextView
    private lateinit var adapter: ProductAdapter
    lateinit var check:CheckBox

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        emptyView = findViewById(R.id.emptyView)
        dbHelper = DBHelper(this)
        adapter = ProductAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        displayProducts()
    }

    private fun displayProducts() {
        val products = dbHelper.getAllProducts()
        if (products.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
            adapter.products = products
        }
    }

    fun addToCard(view: View) {
        var count = recyclerView.childCount
        var list = mutableListOf<String>()

        for ( i in 0 until count) {
            var vh = recyclerView.getChildAt(i)
            val a = vh.findViewById<CheckBox>(R.id.check)
            var name = vh.findViewById<TextView>(R.id.productName)

            if (a.isChecked) {
                list.add(name.text.toString())
            }
        }
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra("list", ArrayList<String>(list))
        startActivity(intent)
    }
}

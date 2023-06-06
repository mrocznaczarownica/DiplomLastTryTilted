package com.example.diplomlasttrytilted.userActivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.auxiliaryClasses.ProductAdapter
import com.example.diplomlasttrytilted.auxiliaryClasses.ProductAdapterNotLocal
import com.example.diplomlasttrytilted.dataBase.DBHelper
import com.example.diplomlasttrytilted.dataBase.Tarif

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

        /*MyAsyncTask().execute()

        // Создаем адаптер и устанавливаем его для RecyclerView
        adapter = ProductAdapter(products)
        recyclerView.adapter = adapter*/

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

    fun getName(): MutableList<String> {
        check = product_item.findViewById(R.id.check)
        var toCartList = mutableListOf<String>()

        for (i in 0 until check.lineCount) {
            if (check.isChecked) {
                var name:TextView = findViewById(R.id.productName)
                toCartList.add(name.text.toString())
            }
        }

        return toCartList
    }

    fun addToCard(view: View) {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }
}

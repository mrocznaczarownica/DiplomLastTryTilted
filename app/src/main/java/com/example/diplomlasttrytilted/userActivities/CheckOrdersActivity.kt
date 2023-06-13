package com.example.diplomlasttrytilted.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.auxiliaryClasses.CartItemsAdapter
import com.example.diplomlasttrytilted.auxiliaryClasses.OrdersAdapter
import com.example.diplomlasttrytilted.dataBase.DBHelper
import com.example.diplomlasttrytilted.dataBase.Order
import java.util.*

class CheckOrdersActivity : AppCompatActivity() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var ordersList: List<Order>
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_orders)

        dbHelper = DBHelper(this)
        ordersRecyclerView = findViewById(R.id.orders_recycler_view)
        ordersRecyclerView.layoutManager = LinearLayoutManager(this)
        ordersList = getOrders()
        ordersAdapter = OrdersAdapter(ordersList)
        ordersRecyclerView.adapter = ordersAdapter
    }

    private fun getOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val date = getCurrentDateTime()
        //val items = dbHelper.getItemFromOrder()
        val bundle = intent.extras
        val list = bundle?.getStringArrayList("list")
        var cart:CartActivity = CartActivity()
        orders.add(Order(cart.list[0], cart.list[1].toInt(),cart.quantity, date.toString()))
        return orders
    }
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}
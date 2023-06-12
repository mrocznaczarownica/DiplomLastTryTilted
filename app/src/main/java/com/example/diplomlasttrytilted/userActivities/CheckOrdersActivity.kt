package com.example.diplomlasttrytilted.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.auxiliaryClasses.OrdersAdapter
import com.example.diplomlasttrytilted.dataBase.Order

class CheckOrdersActivity : AppCompatActivity() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var ordersList: List<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_orders)

        // Инициализация RecyclerView и адаптера
        ordersRecyclerView = findViewById(R.id.orders_recycler_view)
        ordersRecyclerView.layoutManager = LinearLayoutManager(this)
        ordersAdapter = OrdersAdapter(this)
        ordersRecyclerView.adapter = ordersAdapter

        // Получение списка заказов из базы данных или другого источника
        ordersList = getOrders()

        // Установка списка заказов в адаптер
        ordersAdapter.setOrders(ordersList)
    }

    private fun getOrders(): List<Order> {
        // Здесь можно получить список заказов из базы данных или другого источника
        // В этом примере мы просто создаем несколько заказов для отображения
        val orders = mutableListOf<Order>()
        orders.add(Order("Заказ 1", "10.05.2023"))
        orders.add(Order("Заказ 2", "12.05.2023"))
        orders.add(Order("Заказ 3", "15.05.2023"))
        return orders
    }
}
package com.example.diplomlasttrytilted.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.auxiliaryСlasses.CartItem
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.auxiliaryClasses.CartItemsAdapter
import com.example.diplomlasttrytilted.auxiliaryClasses.ProductAdapter
import com.example.diplomlasttrytilted.dataBase.DBHelper
import com.example.diplomlasttrytilted.dataBase.Product
import com.example.diplomlasttrytilted.dataBase.Tarif
import java.net.URLEncoder
import java.sql.DriverManager

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerViewCartItems: RecyclerView
    private lateinit var adapter: CartItemsAdapter

    private lateinit var textViewTotalPrice: TextView
    private lateinit var editTextCustomerName: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var buttonPlaceOrder: Button

    private lateinit var cartItems: List<CartItem>
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        dbHelper = DBHelper(this)

        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)

        val catalogActivity = CatalogActivity()
        val list = catalogActivity.getName()
        for (item in list){
            val products = dbHelper.getProductsForName(item)
            if(products.isEmpty()){
                Toast.makeText(this, "Нет элементов в корзине", Toast.LENGTH_SHORT).show()
            }
            else{
                adapter = CartItemsAdapter(products as MutableList<Tarif>)
                val totalPrice = calculateTotalPrice(cartItems)
                textViewTotalPrice.text = totalPrice.toString()
            }
        }

        /*recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice)
        editTextCustomerName = findViewById(R.id.editTextCustomerName)
        editTextAddress = findViewById(R.id.editTextAddress)
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder)

        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)
        cartItems = getCartItems()
        //val cartAdapter = CartAdapter(cartItems)
        //recyclerViewCartItems.adapter = cartAdapter

        val totalPrice = calculateTotalPrice(cartItems)
        //textViewTotalPrice.text = getString(R.string.total_price, totalPrice)

        buttonPlaceOrder.setOnClickListener {
            placeOrder()
        */}

        /*// Получаем данные из удаленной базы данных
        val products = mutableListOf<Product>()
        val connString =
            "jdbc:sqlserver://hnt8.ru:3333;databaseName=FS;user=Darya;password=okvpA37TB"
        val query = "SELECT * FROM Product"
        val url = "http://hnt8.ru:3333/query?queryString=" + URLEncoder.encode(query, "UTF-8")

        // Устанавливаем соединение с базой данных и выполняем запрос
        val conn = DriverManager.getConnection(connString)
        val stmt = conn.createStatement()
        val resultSet = stmt.executeQuery(query)

        // Создаем модель данных и заполняем ее результатами запроса
        while (resultSet.next()) {
            val name = resultSet.getString("name")
            val description = resultSet.getString("description")
            val price = resultSet.getInt("price")
            val image = resultSet.getString("image")
            val product = Product(name, description, price, image)
            products.add(product)
        }

        // Создаем адаптер и устанавливаем его для RecyclerView
        adapter = CartItemsAdapter(products)
        recyclerViewCartItems.adapter = adapter

        // Закрываем ресурсы
        resultSet.close()
        stmt.close()
        conn.close()*/
    }
    /*private fun getCartItems(): List<CartItem> {
        //  здесь код для получения списка товаров из корзины
        val shoppingCart = ShoppingCart.getInstance(this)
        return shoppingCart.getCartItems()
    }*/

    private fun calculateTotalPrice(cartItems: List<CartItem>): Double {
        //  здесь код для расчета общей стоимости заказа на основе списка товаров
        return 0.0
    }

    private fun placeOrder() {
        /*val customerName = editTextCustomerName.text.toString()
        val address = editTextAddress.text.toString()
        val totalPrice = calculateTotalPrice(cartItems)*/

        // Создайте здесь код для отправки заказа на сервер и очистки корзины в локальном хранилище
        //ShoppingCart.getInstance(this).clearCart()

        //Toast.makeText(this, "Ваш заказ на сумму $totalPrice принят", Toast.LENGTH_SHORT).show()
        //finish()
    }


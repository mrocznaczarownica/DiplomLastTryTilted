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

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerViewCartItems: RecyclerView
    private lateinit var textViewTotalPrice: TextView
    private lateinit var editTextCustomerName: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var buttonPlaceOrder: Button

    private lateinit var cartItems: List<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

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
        }*/
    }

    /*private fun getCartItems(): List<CartItem> {
        //  здесь код для получения списка товаров из корзины
        //val shoppingCart = ShoppingCart.getInstance(this)
        return shoppingCart.getCartItems()
    }

    private fun calculateTotalPrice(cartItems: List<CartItem>): Double {
        //  здесь код для расчета общей стоимости заказа на основе списка товаров
        return 0.0
    }

    private fun placeOrder() {
        val customerName = editTextCustomerName.text.toString()
        val address = editTextAddress.text.toString()
        val totalPrice = calculateTotalPrice(cartItems)

        // Создайте здесь код для отправки заказа на сервер и очистки корзины в локальном хранилище
        //ShoppingCart.getInstance(this).clearCart()

        Toast.makeText(this, "Ваш заказ на сумму $totalPrice принят", Toast.LENGTH_SHORT).show()
        finish()
    }*/
}
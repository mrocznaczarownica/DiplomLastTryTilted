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
import com.example.diplomlasttrytilted.dataBase.DBHelper

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerViewCartItems: RecyclerView
    private lateinit var adapter: CartItemsAdapter
    var quantity: Int = 1

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

        val bundle = intent.extras
        val list = bundle?.getStringArrayList("list")

        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)

        if (list != null) {
            for (item in list) {
                //TODO:"1)написать в хелпере функцию для получения данных о товарах по имени в формате MutableList<Tarif> и кинуть этот лист в адаптер
                // "2) нужно продумать момент перехода в корзину не через каталог а из главного меню"
                val data = dbHelper.getProductsForName(item)
                dbHelper.addItemToCart(data)

                adapter = CartItemsAdapter(data)
                val totalPrice = calculateTotalPrice(cartItems)
                textViewTotalPrice.text = totalPrice.toString()
            }
        } else {
            Toast.makeText(this, "Нет элементов в корзине", Toast.LENGTH_SHORT).show()
        }

        //TODO:можно в $cart_item добавить кнопки для увеличения количества элементов
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice)
        editTextCustomerName = findViewById(R.id.editTextCustomerName)
        editTextAddress = findViewById(R.id.editTextAddress)
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder)

        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)
        cartItems = getCartItems()
        val cartAdapter = CartAdapter(cartItems)
        //recyclerViewCartItems.adapter = cartAdapter

        //val totalPrice = calculateTotalPrice(cartItems)
        //textViewTotalPrice.text = getString(R.string.total_price, totalPrice)

        buttonPlaceOrder.setOnClickListener {
            placeOrder()
        }


        // Создаем адаптер и устанавливаем его для RecyclerView
        /*adapter = CartItemsAdapter(products)
        recyclerViewCartItems.adapter = adapter*/

        // Закрываем ресурсы
        /*resultSet.close()
        stmt.close()
        conn.close()*/
    }

    private fun getCartItems(): List<CartItem> {
        val shoppingCart = dbHelper.getItemFromCart()
        var result : List<CartItem> = arrayListOf(CartItem(shoppingCart, quantity))
        return result
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
    }
}


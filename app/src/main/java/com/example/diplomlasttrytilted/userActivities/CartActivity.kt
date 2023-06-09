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
import com.example.diplomlasttrytilted.dataBase.Tarif

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
    lateinit var data :List<Tarif>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        dbHelper = DBHelper(this)
        //TODO:можно в $cart_item добавить кнопки для увеличения количества элементов
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice)
        editTextCustomerName = findViewById(R.id.editTextCustomerName)
        editTextAddress = findViewById(R.id.editTextAddress)
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder)

        val bundle = intent.extras
        val list = bundle?.getStringArrayList("list")

        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)
        //cartItems = getCartItems()

        if (list != null) {
            for (item in list) {
                //TODO:"1)написать в хелпере функцию для получения данных о товарах по имени в формате MutableList<Tarif> и кинуть этот лист в адаптер
                // "2) нужно продумать момент перехода в корзину не через каталог а из главного меню"
                data = dbHelper.getProductsForName(item.toString())
                dbHelper.addItemToCart(data)
            }
            adapter = CartItemsAdapter(data)
            recyclerViewCartItems.adapter = adapter

        } else {
            Toast.makeText(this, "Нет элементов в корзине", Toast.LENGTH_SHORT).show()
        }

        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)

        val cartAdapter = CartItemsAdapter(dbHelper.getItemFromCart())
        recyclerViewCartItems.adapter = cartAdapter

        textViewTotalPrice.text = getString(R.string.total_price)

        buttonPlaceOrder.setOnClickListener {
            if(editTextAddress.text.toString().isNotEmpty() && editTextCustomerName.text.toString().isNotEmpty()) {
                placeOrder()
                dbHelper.deleteItemFromCart()
                list?.clear()
            }else{
                Toast.makeText(this, "Заполните адрес и имя пользователя", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateTotalPrice(cartItems: List<CartItem>): Double {
        //  здесь код для расчета общей стоимости заказа на основе списка товаров
        return 0.0
    }

    private fun placeOrder() {
        Toast.makeText(this, "Ваш заказ на сумму принят", Toast.LENGTH_SHORT).show()
        finish()
    }
}


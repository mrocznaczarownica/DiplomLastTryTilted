package com.example.diplomlasttrytilted.userActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.auxiliaryСlasses.CartItem
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.auxiliaryClasses.CartItemsAdapter
import com.example.diplomlasttrytilted.dataBase.DBHelper
import com.example.diplomlasttrytilted.dataBase.Tarif

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerViewCartItems: RecyclerView
    var quantity: Int = 1

    private lateinit var textViewTotalPrice: TextView
    private lateinit var adapter: CartItemsAdapter
    private lateinit var editTextCustomerName: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var buttonPlaceOrder: Button

    private lateinit var cartItems: List<CartItem>
    private lateinit var dbHelper: DBHelper
    lateinit var data :List<Tarif>
    lateinit var data3 :MutableList<Tarif>
    lateinit var data2 :List<String>

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
        data3 = mutableListOf()

        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems)
        recyclerViewCartItems.layoutManager = LinearLayoutManager(this)

        if (list != null) {
            for ( i in 0 until list.size) {
                data2 = dbHelper.getProductsForName(list[i].toString())
                data3.add(Tarif(data2[0], data2[1], data2[2].toInt(),data2[3]))
                dbHelper.addItemToCart(arrayListOf(data2[0], data2[1], data2[2],data2[3]))
            }
            adapter = CartItemsAdapter(data3)
            recyclerViewCartItems.adapter = adapter
        } else {
            Toast.makeText(this, "Нет элементов в корзине", Toast.LENGTH_SHORT).show()
        }
        textViewTotalPrice.text = adapter.sum.toString()

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
        Toast.makeText(this, "Ваш заказ принят", Toast.LENGTH_SHORT).show()
        intent.putStringArrayListExtra("list", ArrayList<String>(data2))
        val intent = Intent(this, CheckOrdersActivity::class.java)
        startActivity(intent)
    }
}


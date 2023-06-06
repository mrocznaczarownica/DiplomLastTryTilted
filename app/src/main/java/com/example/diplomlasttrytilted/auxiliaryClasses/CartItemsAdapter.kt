package com.example.diplomlasttrytilted.auxiliaryClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.sql.*
import java.net.*
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.auxiliaryСlasses.CartItem
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.dataBase.Product
import com.example.diplomlasttrytilted.dataBase.Tarif

class CartItemsAdapter(private val products: MutableList<Tarif>): RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
        val quantity: TextView = itemView.findViewById(R.id.item_quantity)
        val image: ImageView = itemView.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.name
        holder.image.setImageResource(product.image.toInt())
        holder.price.text = product.price.toString()
        holder.quantity.text = "1"
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
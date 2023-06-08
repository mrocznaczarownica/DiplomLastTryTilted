package com.example.diplomlasttrytilted.auxiliaryClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.dataBase.Tarif
import com.example.diplomlasttrytilted.R

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var products: List<Tarif> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        //holder.productImage.setImageResource(product.imageResId)
        holder.productName.text = product.name
        holder.productPrice.text = holder.itemView.context.getString(R.string.product_price, product.price)
    }

    override fun getItemCount() = products.size
}
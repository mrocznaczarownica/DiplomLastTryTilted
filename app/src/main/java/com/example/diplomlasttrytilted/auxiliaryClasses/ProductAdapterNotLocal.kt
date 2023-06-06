package com.example.diplomlasttrytilted.auxiliaryClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.dataBase.Product

class ProductAdapterNotLocal(private val products: MutableList<Product>) : RecyclerView.Adapter<ProductAdapterNotLocal.ProductViewHolder>() {

    var products1: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.Name
        holder.productPrice.text = product.Price.toString()
        //holder.productImage.setImageResource(product.Image.toInt())
    }

    override fun getItemCount() = products.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
        val image: ImageView = itemView.findViewById(R.id.item_image)
    }

}


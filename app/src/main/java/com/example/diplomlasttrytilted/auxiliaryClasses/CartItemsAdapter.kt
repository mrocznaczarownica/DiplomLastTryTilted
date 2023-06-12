package com.example.diplomlasttrytilted.auxiliaryClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.dataBase.Cart
import com.example.diplomlasttrytilted.dataBase.Tarif
import com.example.diplomlasttrytilted.userActivities.CartActivity as CartActivity1

class CartItemsAdapter(private val products: List<Tarif>): RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
        val quantity: TextView = itemView.findViewById(R.id.item_quantity)
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val plus: ImageButton = itemView.findViewById(R.id.plus)
        val minus:ImageButton = itemView.findViewById(R.id.minus)
        val error:TextView = itemView.findViewById(R.id.errorTextView)
        val total:TextView = itemView.findViewById(R.id.total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.name
        //holder.image.setImageResource(product.image.toInt())
        holder.price.text = product.price.toString() + " руб."
        var qua = 1
        val max = 5
        holder.quantity.text = qua.toString()

        holder.plus.setOnClickListener{
            if(qua < max){
                qua += 1
                holder.quantity.text = qua.toString()
                var b = product.price.toString().toInt() * qua
                holder.error.text = ""
                holder.total.text = "Сумма: $b"
            }
            else{
                holder.error.text = "Этого товара больше нет на складе"
            }
        }

        holder.minus.setOnClickListener {
            if(qua > 0){
                qua -= 1
                holder.quantity.text = qua.toString()
                holder.error.text = ""
                var b = product.price.toString().toInt() * qua
                holder.total.text = "Сумма: $b"
            }
            else{
                holder.error.text = "Количество товара не может быть меньше нуля"
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}


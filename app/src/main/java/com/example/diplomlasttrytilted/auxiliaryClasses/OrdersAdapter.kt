package com.example.diplomlasttrytilted.auxiliaryClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.dataBase.Order

class OrdersAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val price:TextView = itemView.findViewById(R.id.item_price)
        val quantity:TextView = itemView.findViewById(R.id.item_quantity)
        val total:TextView = itemView.findViewById(R.id.item_total_price)
        val date:TextView = itemView.findViewById(R.id.item_date)
        fun bind(order: Order) {
            itemView.findViewById<TextView>(R.id.item_name).text = order.nameProduct
            itemView.findViewById<TextView>(R.id.item_price).text = order.price.toString()
            itemView.findViewById<TextView>(R.id.item_quantity).text = order.quantity.toString()
            itemView.findViewById<TextView>(R.id.item_total_price).text = (price.text.toString().toInt() * quantity.text.toString().toInt()).toString()
            itemView.findViewById<TextView>(R.id.item_date).text = order.date.toString()
        }
    }
}

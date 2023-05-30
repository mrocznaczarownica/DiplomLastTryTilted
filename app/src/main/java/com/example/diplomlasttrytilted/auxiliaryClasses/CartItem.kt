package com.example.diplom.auxiliaryСlasses

import com.example.diplom.database.ProductInfo
import com.example.diplom.database.Tarif

class CartItem(val product: Tarif, var quantity: Int) {
    fun getTotalPrice(): Int {
        return product.price * quantity
    }
}
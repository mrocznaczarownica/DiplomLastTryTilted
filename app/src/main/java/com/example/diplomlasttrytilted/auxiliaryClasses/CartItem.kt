package com.example.diplom.auxiliaryСlasses

import com.example.diplomlasttrytilted.dataBase.Tarif

class CartItem(val product: Tarif, var quantity: Int) {
    fun getTotalPrice(): Int {
        return product.price * quantity
    }
}
package com.example.diplomlasttrytilted.dataBase

import java.util.Date

data class Order(
    var nameProduct:String,
    var price:Int,
    var quantity:Int,
    var date:Date
)

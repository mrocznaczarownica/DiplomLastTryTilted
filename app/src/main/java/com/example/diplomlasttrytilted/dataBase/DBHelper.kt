package com.example.diplomlasttrytilted.dataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_CLIENTS = "Clients"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_MIDDLE_NAME = "middle_name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_LOGIN = "login"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_USERS = "users"
        private const val COLUMN_ID_ROLE = "idRole"
        private const val COLUMN_LOGIN_USERS = "login"
        private const val COLUMN_PASSWORD_USERS = "password"
        private const val COLUMN_ROL = "rol"

        private const val TABLE_TARIFS = "Tarif"
        private const val COLUMN_NAME_TARIF = "name"
        private const val COLUMN_DESCTIPTION = "desctiption"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_IMAGE = "image"

        private const val TABLE_CART = "Cart"
        private const val COLUMN_NAME_CART = "name"
        private const val COLUMN_DESCTIPTION_CART = "desctiption"
        private const val COLUMN_PRICE_CART = "price"
        private const val COLUMN_IMAGE_CART = "image"

        private const val TABLE_CONSULT = "Consultation"
        private const val COLUMN_FNAME_CONSULT = "firstName"
        private const val COLUMN_NAME_CONSULT = "name"
        private const val COLUMN_LNAME_CONSULT = "lastName"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_PHONE_CONSULT = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_CLIENTS " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRST_NAME TEXT, " +
                "$COLUMN_LAST_NAME TEXT, " +
                "$COLUMN_MIDDLE_NAME TEXT, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_LOGIN TEXT UNIQUE, " +
                "$COLUMN_PASSWORD TEXT)"

        val tarifTable = "CREATE TABLE $TABLE_TARIFS " +
                "($COLUMN_NAME_TARIF TEXT, " +
                "$COLUMN_DESCTIPTION TEXT, " +
                "$COLUMN_PRICE INT, " +
                "$COLUMN_IMAGE TEXT)"

        val cartTable = "CREATE TABLE $TABLE_CART " +
                "($COLUMN_NAME_CART TEXT, " +
                "$COLUMN_DESCTIPTION_CART TEXT, " +
                "$COLUMN_PRICE_CART INT, " +
                "$COLUMN_IMAGE_CART TEXT)"

        val consultTable = "CREATE TABLE $TABLE_CONSULT " +
                "($COLUMN_FNAME_CONSULT TEXT, " +
                "$COLUMN_NAME_CONSULT TEXT, " +
                "$COLUMN_LNAME_CONSULT INT, " +
                "$COLUMN_DATE TEXT, " +
                "$COLUMN_PHONE_CONSULT TEXT)"

        db?.execSQL(tarifTable)
        db?.execSQL(createTable)
        db?.execSQL(cartTable)
        db?.execSQL(consultTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_TARIFS'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_CLIENTS'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_CART'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_CONSULT'")
        onCreate(db)
    }

    fun addClient(user: Clients) {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COLUMN_FIRST_NAME, user.firstName)
        contentValues.put(COLUMN_LAST_NAME, user.lastName)
        contentValues.put(COLUMN_MIDDLE_NAME, user.middleName)
        contentValues.put(COLUMN_PHONE, user.phoneNumber)
        contentValues.put(COLUMN_LOGIN, user.login)
        contentValues.put(COLUMN_PASSWORD, user.password)

        db.insert(TABLE_CLIENTS, null, contentValues)
        db.close()
    }

    fun deleteClient(login: String) {
        val db = this.writableDatabase
        db.delete(TABLE_CLIENTS, "$COLUMN_LOGIN = ?", arrayOf(login))
        db.close()
    }

    fun getUserByLogin(login: String): Clients? {
        val db = this.readableDatabase

        val cursor = db.query(TABLE_CLIENTS,
            arrayOf(COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_MIDDLE_NAME, COLUMN_PHONE,
                COLUMN_LOGIN, COLUMN_PASSWORD),
            "$COLUMN_LOGIN = ?",
            arrayOf(login),
            null, null, null, null)

        val user: Clients?
        if (cursor.moveToFirst()) {
            val firstName = cursor.getString(0)
            val lastName = cursor.getString(1)
            val middleName = cursor.getString(2)
            val phoneNumber = cursor.getString(3)
            val login = cursor.getString(4)
            val password = cursor.getString(5)
            user = Clients( firstName, lastName, middleName,
                phoneNumber,login, password)
        } else {
            user = null
        }

        cursor.close()
        db.close()
        return user
    }

    fun getUserByLoginAndPassword(login: String, password: String): Clients? {

        /*val db1 = this.writableDatabase

        val createTable = "CREATE TABLE $TABLE_CLIENTS " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRST_NAME TEXT, " +
                "$COLUMN_LAST_NAME TEXT, " +
                "$COLUMN_MIDDLE_NAME TEXT, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_LOGIN TEXT UNIQUE, " +
                "$COLUMN_PASSWORD TEXT)"
        db1?.execSQL(createTable)*/

        val db = this.readableDatabase

        val cursor = db.query(
            TABLE_CLIENTS,
            arrayOf(COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_MIDDLE_NAME, COLUMN_PHONE),
            "$COLUMN_LOGIN = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(login, password),
            null, null, null, null)

        val user: Clients?
        if (cursor.moveToFirst()) {
            val firstName = cursor.getString(0)
            val lastName = cursor.getString(1)
            val middleName = cursor.getString(2)
            val phoneNumber = cursor.getString(3)
            user = Clients(firstName, lastName, middleName, phoneNumber, login, password)
            user.login = login
            user.password = password
        } else {
            user = null
        }

        cursor.close()
        db.close()
        return user
    }

    fun getAllProducts(): List<Tarif> {
        /*val db1 = this.writableDatabase
        val tarifTable = "CREATE TABLE $TABLE_TARIFS " +
                "($COLUMN_NAME_TARIF TEXT, " +
                "$COLUMN_DESCTIPTION TEXT, " +
                "$COLUMN_PRICE INT, " +
                "$COLUMN_IMAGE TEXT)"
        db1?.execSQL(tarifTable)*/

        /*val db2 = this.writableDatabase
        val tarifTable1 = "INSERT INTO $TABLE_TARIFS VALUES" +
                *//*"('Гроб лакированный', 'Описания нет', 12000, 'image')," +*//*
                "('Гроб из красного дерева', 'Описания нет', 15000, 'image')"
        db2?.execSQL(tarifTable1)*/

        /*val db2 = this.writableDatabase
        val tarifTable1 = "INSERT INTO $TABLE_TARIFS VALUES" +
                "('Гроб лакированный', 'Описания нет', 12000, 'image')," +
                "('Кремация', 'Описания нет', 20000, 'image')," +
                "('Гроб Дизайнерский', 'Описания нет', 30000, 'image')"
//                "('Гроб из красного дерева', 'Описания нет', 15000, 'image')"
        db2?.execSQL(tarifTable1)
*/
        val stdList: ArrayList<Tarif> = ArrayList()

        val query = "SELECT * FROM $TABLE_TARIFS"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query,null)
        }
        catch (e:Exception)
        {
            db.execSQL(query)
            return ArrayList()
        }
        var name:String
        var desctiption: String
        var price: Int
        var image: String
        if(cursor.moveToFirst())
        {
            do {
                name = cursor.getString(0)
                desctiption = cursor.getString(1)
                price = cursor.getInt(2)
                image = cursor.getString(3)
                val std = Tarif(name = name, desctiption = desctiption, price = price, image = image)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        return stdList
    }

    fun getProductsForName(name:String): List<Tarif> {
        /*val db1 = this.writableDatabase
        val tarifTable = "CREATE TABLE $TABLE_TARIFS " +
                "($COLUMN_NAME_TARIF TEXT, " +
                "$COLUMN_DESCTIPTION TEXT, " +
                "$COLUMN_PRICE INT, " +
                "$COLUMN_IMAGE TEXT)"
        db1?.execSQL(tarifTable)*/

        /*TODO: прогнать класс в таком порядке: 1.удаление таблицы тариф
        2. создание таблицы
        3.добавление новых данных, обязательно сменить картинки(на компе у юли)
        далее тест корзины и заказа с новыми данными*/
        /*val db2 = this.writableDatabase
        val tarifTable1 = "INSERT INTO $TABLE_TARIFS VALUES" +
                "('Гроб лакированный', 'Описания нет', 12000, 'image')," +
                "('Кремация', 'Описания нет', 20000, 'image')," +
                "('Гроб Дизайнерский', 'Описания нет', 30000, 'image')"
//                "('Гроб из красного дерева', 'Описания нет', 15000, 'image')"
        db2?.execSQL(tarifTable1)
*/
        val stdList: ArrayList<Tarif> = ArrayList()
        var std:Tarif

        val query = "SELECT * FROM $TABLE_TARIFS where $COLUMN_NAME_TARIF = '$name'"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            //cursor = db.rawQuery(query,null)
            cursor = db.query(TABLE_TARIFS,
                arrayOf(COLUMN_NAME_TARIF, COLUMN_DESCTIPTION, COLUMN_PRICE, COLUMN_IMAGE),
                "COLUMN_NAME_TARIF = ?", arrayOf(name),
                null, null, null, null)
        }
        catch (e:Exception)
        {
            db.rawQuery(query, null)
            Log.e("text", e.message.toString())
            return ArrayList()
        }
        var name:String
        var desctiption: String
        var price: Int
        var image: String
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0)
                    desctiption = cursor.getString(1)
                    price = cursor.getInt(2)
                    image = cursor.getString(3)
                    //std = Tarif(name = name, desctiption = desctiption, price = price, image = image)
                    stdList.add(Tarif(name, desctiption, price, image))
                    //stdList.add(Tarif(name = name, desctiption = desctiption, price = price, image = image))
                } while (cursor.moveToNext())
            }
        }
        return stdList
    }

    public fun getUserFromDatabase(): Clients {

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_CLIENTS"

        val cursor = db.rawQuery(query,null)

        cursor.moveToFirst()
        val firstName = cursor.getString(1)
        val name = cursor.getString(2)
        val lastName = cursor.getString(3)
        val phone = cursor.getString(4)
        val login = cursor.getString(5)
        val pass = cursor.getString(6)
        return Clients(firstName, name, lastName, phone, login, pass)
    }

    fun addItemToCart(item: List<String>) {
        val db = this.writableDatabase
        /*val tarifTable1 = "DROP TABLE $TABLE_CART"
        db?.execSQL(tarifTable1)
        val cartTable = "CREATE TABLE $TABLE_CART " +
                "($COLUMN_NAME_CART TEXT, " +
                "$COLUMN_DESCTIPTION_CART TEXT, " +
                "$COLUMN_PRICE_CART INT, " +
                "$COLUMN_IMAGE_CART TEXT)"

        db?.execSQL(cartTable)*/
        //var list:List<String> = arrayListOf("Гроб из красного дерева", "Описания нет", "15000", "image")

        val contentValues = ContentValues()

        contentValues.put(COLUMN_NAME_CART, item.get(0))
        contentValues.put(COLUMN_DESCTIPTION_CART, item.get(1))
        contentValues.put(COLUMN_PRICE_CART, item.get(2))
        contentValues.put(COLUMN_IMAGE_CART, item.get(3))

        db.insert(TABLE_CART, null, contentValues)
        db.close()
    }

    public fun getItemFromCart(): List<Tarif>{

        /*val db1 = this.writableDatabase
        val cartTable = "CREATE TABLE $TABLE_CART " +
                "($COLUMN_NAME_CART TEXT, " +
                "$COLUMN_DESCTIPTION_CART TEXT, " +
                "$COLUMN_PRICE_CART INT, " +
                "$COLUMN_IMAGE_CART TEXT)"

        db1?.execSQL(cartTable)*/

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_CART"

        val cursor = db.rawQuery(query,null)

        cursor.moveToFirst()
        val name = cursor.getString(0)
        val desc = cursor.getString(1)
        val price = cursor.getInt(2)
        val image = cursor.getString(3)
        val std = Tarif(name, desc, price, image)
        val stdList = arrayListOf<Tarif>()
        stdList.add(std)
        return stdList
    }

    fun deleteItemFromCart() {
        val db = this.writableDatabase
        db.delete(TABLE_CART, null, null)
        db.close()
    }
}
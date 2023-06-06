package com.example.diplomlasttrytilted.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


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
        private const val COLUMN_ID_TARIF = "id"
        private const val COLUMN_NAME_TARIF = "name"
        private const val COLUMN_DESCTIPTION = "desctiption"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_IMAGE = "image"
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


        db?.execSQL(tarifTable)
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS '$TABLE_TARIFS'")
            db.execSQL("DROP TABLE IF EXISTS '$TABLE_CLIENTS'")
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
/*        val db1 = this.writableDatabase
        val tarifTable = "CREATE TABLE $TABLE_TARIFS " +
                "($COLUMN_NAME_TARIF TEXT, " +
                "$COLUMN_DESCTIPTION TEXT, " +
                "$COLUMN_PRICE INT, " +
                "$COLUMN_IMAGE TEXT)"
        db1?.execSQL(tarifTable)*/

        /*val db1 = this.writableDatabase
        val tarifTable = "INSERT INTO $TABLE_TARIFS VALUES" +
                "('Гроб лакированный', 'Описания нет', 12000, 'image')"
        db1?.execSQL(tarifTable)*/

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
/*        val db1 = this.writableDatabase
        val tarifTable = "CREATE TABLE $TABLE_TARIFS " +
                "($COLUMN_NAME_TARIF TEXT, " +
                "$COLUMN_DESCTIPTION TEXT, " +
                "$COLUMN_PRICE INT, " +
                "$COLUMN_IMAGE TEXT)"
        db1?.execSQL(tarifTable)*/

        /*val db1 = this.writableDatabase
        val tarifTable = "INSERT INTO $TABLE_TARIFS VALUES" +
                "('Гроб лакированный', 'Описания нет', 12000, 'image')"
        db1?.execSQL(tarifTable)*/

        val stdList: ArrayList<Tarif> = ArrayList()

        val query = "SELECT * FROM $TABLE_TARIFS where name = $name"
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
}
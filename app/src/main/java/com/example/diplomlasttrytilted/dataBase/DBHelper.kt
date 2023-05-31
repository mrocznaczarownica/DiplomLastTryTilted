package com.example.diplomlasttrytilted.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.diplom.database.Clients
import com.example.diplom.database.Tarif
import com.example.diplom.database.User


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_CLIENTS = "users"
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

        private const val TABLE_TARIF = "Tarif"
        private const val COLUMN_ID_TARIF = "id"
        private const val COLUMN_NAME_TARIF = "name"
        private const val COLUMN_DESCTIPTION = "description"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_CLIENTS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRST_NAME TEXT, " +
                "$COLUMN_LAST_NAME TEXT, " +
                "$COLUMN_MIDDLE_NAME TEXT, " +
                "$COLUMN_PHONE TEXT, " +
                "$COLUMN_LOGIN TEXT UNIQUE, " +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // Например, если нам нужно добавить новое поле к таблице
            // val addColumn = "ALTER TABLE $TABLE_USERS ADD COLUMN $COLUMN_NEW_FIELD TEXT"
            // db?.execSQL(addColumn)
        }
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

    /*fun getAllProducts(): List<Tarif>? {
        val productList: MutableList<Tarif> = ArrayList<Tarif>()
        val selectQuery = "SELECT * FROM $TABLE_TARIF"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val product = Tarif()
                product.id = cursor.getString(0)
                product.name = cursor.getString(1)
                product.desctiption = cursor.getString(2)
                product.price = cursor.getInt(3)
                product.image = cursor.getString(4)
                productList.add(product)
            } while (cursor.moveToNext())
        }
        db.close()
        return productList
    }*/

    fun getAllProducts(): List<Tarif> {
        val productList = ArrayList<Tarif>()

        val selectQuery = "SELECT * FROM $TABLE_TARIF"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                var product:Tarif = Tarif(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getInt(3), cursor.getString(4))
                productList.add(product)
            } while (cursor.moveToNext())
        }

        db.close()

        return productList
    }

    public fun getUserFromDatabase(): Clients {
        val db = this.readableDatabase
        val cursor = db.query(
            "users",
            arrayOf("firstName", "name", "lastName", "phone", "login", "pass"),
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToFirst()
        val firstName = cursor.getString(0)
        val name = cursor.getString(1)
        val lastName = cursor.getString(2)
        val phone = cursor.getString(4)
        val login = cursor.getString(5)
        val pass = cursor.getString(6)
        return Clients(firstName, name, lastName, phone, login, pass)
    }
}
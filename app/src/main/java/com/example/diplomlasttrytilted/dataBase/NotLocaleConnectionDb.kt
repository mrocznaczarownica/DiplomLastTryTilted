package com.example.diplom.database
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class NotLocaleConnectionDb(private val url: String, private val username: String, private val password: String) {

    private var connection: Connection? = null

    init {
        try {
            Class.forName("com.mysql.jdbc.Driver") // Указываем драйвер для MySQL
            connection = DriverManager.getConnection(url, username, password) // Подключаемся к базе данных
            Log.e("Connection","Подключение к базе данных успешно установлено!")
        } catch (e: SQLException) {
            Log.e("Connection","Ошибка подключения к базе данных: ${e.message}")
        } catch (e: ClassNotFoundException) {
            Log.e("Connection",e.message.toString())
        }
    }

    fun getConnection(): Connection? {
        return connection
    }

    fun closeConnection() {
        try {
            connection?.close()
            Log.e("Connection","Подключение к базе данных успешно закрыто!")
        } catch (e: SQLException) {
            Log.e("Connection","Ошибка закрытия подключения к базе данных: ${e.message}")
        }
    }
}
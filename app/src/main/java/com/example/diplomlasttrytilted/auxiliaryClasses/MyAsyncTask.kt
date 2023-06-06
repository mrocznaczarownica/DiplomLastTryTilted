/*
package com.example.diplomlasttrytilted.auxiliaryClasses

import android.os.AsyncTask
import com.example.diplomlasttrytilted.dataBase.Product
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.sql.DriverManager

class MyAsyncTask : AsyncTask<Void, Void, String>() {

    override fun doInBackground(vararg params: Void?): String {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        // Выполнение сетевого запроса
        val connection = URL("jdbc:sqlserver://hnt8.ru:3333;databaseName=FS;user=Darya;password=okvpA37TB").openConnection() as HttpURLConnection
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val input = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(input))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            return stringBuilder.toString()
        } else {
            throw IOException("Connection error: $responseCode")
        }
    }

    override fun onPostExecute(result: String?) {
        val conn = DriverManager.getConnection("jdbc:sqlserver://hnt8.ru:3333", "Darya", "okvpA37TB");
        val products = mutableListOf<Product>()
        val query = "SELECT * FROM Product"
        val stmt = conn.createStatement()
        val resultSet = stmt.executeQuery(query)

        // Создаем модель данных и заполняем ее результатами запроса
        while (resultSet.next()) {
            val name = resultSet.getString("name")
            val description = resultSet.getString("description")
            val price = resultSet.getInt("price")
            val image = resultSet.getString("image")
            val product = Product(name, description, price, image)
            products.add(product)
        }
        return products
    }
}*/

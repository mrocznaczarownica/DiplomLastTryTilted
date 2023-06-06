package com.example.diplomlasttrytilted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.diplomlasttrytilted.dataBase.DBHelper
import java.sql.DriverManager

class LoginActivity : AppCompatActivity() {

    lateinit var sqlHelper: DBHelper
    public lateinit var log:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sqlHelper = DBHelper(this)
    }

    fun LoginButtonClick(view: View) {
        var login: EditText = findViewById(R.id.logineditText)
        var pass: EditText = findViewById(R.id.passEditText)
        log = login.text.toString()

        if (login.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {

            /*val url = "jdbc:sqlserver://hnt8.ru:3333;databaseName=FS;user=Darya;password=okvpA37TB"

            // Устанавливаем соединение
            val conn = DriverManager.getConnection(url)

            // Выполняем запрос SELECT и выводим результаты в консоль
            val stmt = conn.createStatement()
            val sql = "SELECT * FROM <table>"
            val rs = stmt.executeQuery(sql)
            while (rs.next()) {
                val column1 = rs.getString("column1")
                val column2 = rs.getInt("column2")
                println("COLUMN1: $column1, COLUMN2: $column2")
            }


            // Закрываем ресурсы
            rs.close()
            stmt.close()
            conn.close()*/

            val user = sqlHelper.getUserByLoginAndPassword(login.text.toString(), pass.text.toString())

            if (user != null) {
                // Если пользователь существует, сохраняем его данные в SharedPreferences
                val sharedPrefs = getSharedPreferences("UserInfo", MODE_PRIVATE)
                sharedPrefs.edit()
                    .putInt("idRole", 0)
                    .putString("login", user.login)
                    .putString("pass", user.password)
                    .putInt("rol", 0)
                    .apply()
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("login", login.text.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this,"Неверный логин и/или пароль",Toast.LENGTH_SHORT).show()
                login.text.clear()
                pass.text.clear()
            }
        }else{
            Toast.makeText(this,"Введите логин и/или пароль",Toast.LENGTH_SHORT).show()
        }
    }

    fun SignInLabelClick(view: View) {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }

    fun passViewClick(view: View) {
        var pass: EditText = findViewById(R.id.passEditText)

        Toast.makeText(this,pass.text.toString(),Toast.LENGTH_SHORT).show()
    }
}
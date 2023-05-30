package com.example.diplomlasttrytilted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.diplomlasttrytilted.dataBase.DBHelper

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
        log = login.toString()

        if (login.toString().isNotEmpty() && pass.toString().isNotEmpty()) {
            val user = sqlHelper.getUserByLoginAndPassword(login.toString(), pass.toString())

            if (user != null) {
                // Если пользователь существует, сохраняем его данные в SharedPreferences
                val sharedPrefs = getSharedPreferences("UserInfo", MODE_PRIVATE)
                sharedPrefs.edit()
                    .putInt("idRole", 0)
                    .putString("login", user.login)
                    .putString("pass", user.password)
                    .putInt("rol", 0)
                    .apply()
            }else{
                Toast.makeText(this,"Неверный логин и/или пароль",Toast.LENGTH_SHORT)
                login.text.clear()
                pass.text.clear()
            }
        }
    }

    fun SignInLabelClick(view: View) {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }
}
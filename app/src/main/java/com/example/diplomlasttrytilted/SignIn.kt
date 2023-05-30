package com.example.diplomlasttrytilted

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.diplom.database.Clients
import com.example.diplomlasttrytilted.dataBase.DBHelper

class SignIn : AppCompatActivity() {

    lateinit var sqlHelper: DBHelper
    public lateinit var log:String
    private lateinit var firstNameEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sqlHelper = DBHelper(this)

        firstNameEditText = findViewById(R.id.firstName)
        nameEditText = findViewById(R.id.name)
        lastNameEditText = findViewById(R.id.lastName)
        phoneEditText = findViewById(R.id.phoneNumber)
        loginEditText = findViewById(R.id.login)
        passwordEditText = findViewById(R.id.pass)
        registerButton = findViewById(R.id.registerButton)
        errorTextView = findViewById(R.id.errorTextView)

        registerButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val middleName = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Проверяем, что поля не пустые
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && login.isNotEmpty()
                && password.isNotEmpty() && middleName.isNotEmpty() && phone.isNotEmpty()) {
                // Проверяем, что логин не занят
                if (sqlHelper.getUserByLogin(login) == null) {
                    // Создаем нового пользователя
                    val newUser = Clients( lastName, login, password)

                    // Добавляем пользователя в базу данных
                    sqlHelper.addClient(newUser)

                    // Переходим на окно входа
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    errorTextView.text = "Пользователь с таким логином уже существует"
                }
            } else {
                errorTextView.text = "Заполните все поля"
            }
        }
    }
}
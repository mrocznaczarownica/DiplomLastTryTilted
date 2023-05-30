package com.example.diplomlasttrytilted.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.diplomlasttrytilted.R
import com.example.diplomlasttrytilted.dataBase.DBHelper

class MyProfileActivity : AppCompatActivity() {

    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profilePhone: TextView
    private lateinit var editProfileButton: Button

    private lateinit var dbHelper: DBHelper

    private var isEditMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        dbHelper = DBHelper(this)

        // Получаем ссылки на элементы интерфейса
        profileName = findViewById(R.id.profile_name)
        profileEmail = findViewById(R.id.profile_email)
        profilePhone = findViewById(R.id.profile_phone)
        editProfileButton = findViewById(R.id.edit_profile_button)

        // Заполняем поля данными пользователя
        val user = dbHelper.getUserFromDatabase() // Функция для получения данных пользователя из базы данных
        /*profileName.text = user.name
        profileEmail.text = user.email
        profilePhone.text = user.phone*/
        //TODO:отредактировать окно профиля(добавить недостаюшие поля), отредактировать бэк согласно изменениям

        // Обработка клика на кнопке редактирования профиля
        editProfileButton.setOnClickListener {
            if (!isEditMode) {
                // Переключаемся в режим редактирования
                isEditMode = true
                editProfileButton.text = getString(R.string.save_profile_button)
                enableEditing()
            } else {
                // Сохраняем изменения и переключаемся в режим просмотра
                isEditMode = false
                editProfileButton.text = getString(R.string.edit_profile_button)
                /*disableEditing()
                saveProfileChanges()*/
                //TODO:реализовать две функции выше
            }
        }
    }

    // Включение режима редактирования
    private fun enableEditing() {
        profileName.isEnabled = true
        profileEmail.isEnabled = true
        profilePhone.isEnabled = true
    }
}
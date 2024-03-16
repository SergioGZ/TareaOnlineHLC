package com.example.datastoretarea1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCES")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val etId = findViewById<EditText>(R.id.etId)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etAnioNacimiento = findViewById<EditText>(R.id.etAnioNacimiento)

        btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                saveValues(etId.text.toString(), etNombre.text.toString(), etApellidos.text.toString(), etAnioNacimiento.text.toString().toInt())
            }
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }

    private suspend fun saveValues(id: String, nombre: String, apellidos: String, anioNacimiento: Int) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("id")] = id
            preferences[stringPreferencesKey("nombre")] = nombre
            preferences[stringPreferencesKey("apellidos")] = apellidos
            preferences[intPreferencesKey("anioNacimiento")] = anioNacimiento
        }
    }
}
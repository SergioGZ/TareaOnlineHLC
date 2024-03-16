package com.example.datastoretarea1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val btnLimpiarDatos = findViewById<Button>(R.id.btnLimpiarDatos)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvApellidos = findViewById<TextView>(R.id.tvApellidos)
        val tvAnioNacimiento = findViewById<TextView>(R.id.tvAnioNacimiento)

        btnLimpiarDatos.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                limpiarDatos()
            }
            this.recreate()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            getUserProfile().collect{
                withContext(Dispatchers.Main) {
                    tvId.text = it.id
                    tvNombre.text = it.nombre
                    tvApellidos.text = it.apellidos
                    tvAnioNacimiento.text = it.anioNacimiento.toString()
                }
            }
        }
    }

    private fun getUserProfile() = dataStore.data.map { preferences ->
        UserProfile(
            id = preferences[stringPreferencesKey("id")].orEmpty(),
            nombre = preferences[stringPreferencesKey("nombre")].orEmpty(),
            apellidos = preferences[stringPreferencesKey("apellidos")].orEmpty(),
            anioNacimiento = preferences[intPreferencesKey("anioNacimiento")] ?: 0
        )
    }

    private suspend fun limpiarDatos() {
        dataStore.edit { preferences ->
            preferences.clear()
            //preferences.remove(stringPreferencesKey("id"))
            //preferences.remove(stringPreferencesKey("nombre"))
            //preferences.remove(stringPreferencesKey("apellidos"))
            //preferences.remove(intPreferencesKey("anioNacimiento"))
        }
    }
}
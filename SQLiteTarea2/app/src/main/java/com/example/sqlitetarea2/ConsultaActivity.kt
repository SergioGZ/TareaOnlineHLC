package com.example.sqlitetarea2

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.sqlitetarea2.databinding.ActivityConsultaBinding
import com.example.sqlitetarea2.databinding.ActivityMainBinding

class ConsultaActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var miDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        binding = ActivityMainBinding.inflate(layoutInflater)

        miDBHelper = miSQLiteHelper(this)

        val lvConsulta = findViewById<ListView>(R.id.lvConsulta)
        val db = miDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM agenda", null)
        val lista = ArrayList<String>()
        if (cursor.moveToFirst()) {
            do {
                val nick = cursor.getString(1)
                val movil = cursor.getString(2)
                val apellido1 = cursor.getString(3)
                val apellido2 = cursor.getString(4)
                val nombre = cursor.getString(5)
                val email = cursor.getString(6)
                lista.add("Nick: $nick\nMovil: $movil\nApellido1: $apellido1\nApellido2: $apellido2\nNombre: $nombre\nEmail: $email")
            } while (cursor.moveToNext())
        }
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)
        lvConsulta.adapter = adaptador
        cursor.close()
        db.close()
        Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show()
    }
}
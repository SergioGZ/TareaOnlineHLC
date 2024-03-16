package com.example.sqlitetarea2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.sqlitetarea2.databinding.ActivityMainBinding

class ConsultaTlf : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var miDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_tlf)

        binding = ActivityMainBinding.inflate(layoutInflater)

        miDBHelper = miSQLiteHelper(this)

        val tvContacto = findViewById<TextView>(R.id.tvContacto)
        val intent = intent
        val tlfBuscado = intent.getStringExtra("tlf")

        val db = miDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM agenda WHERE movil = '$tlfBuscado'", null)
        if (cursor.moveToFirst()) {
            val nick = cursor.getString(1)
            val movil = cursor.getInt(2)
            val apellido1 = cursor.getString(3)
            val apellido2 = cursor.getString(4)
            val nombre = cursor.getString(5)
            val email = cursor.getString(6)
            tvContacto.text = "Nick: $nick\nMovil: $movil\nApellido1: $apellido1\nApellido2: $apellido2\nNombre: $nombre\nEmail: $email"
        } else {
            tvContacto.text = "No se ha encontrado el teléfono"
        }
        cursor.close()
        db.close()
    }
}
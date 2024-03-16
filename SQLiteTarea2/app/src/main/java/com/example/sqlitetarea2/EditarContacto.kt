package com.example.sqlitetarea2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sqlitetarea2.databinding.ActivityMainBinding

class EditarContacto : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var miDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contacto)

        binding = ActivityMainBinding.inflate(layoutInflater)

        miDBHelper = miSQLiteHelper(this)

        val etTlf = findViewById<EditText>(R.id.etMovil)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)
        val intent = intent
        val tlfBuscado = intent.getStringExtra("tlf")
        tvMensaje.text = "Editando el contacto con el teléfono $tlfBuscado"

        val db = miDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT movil, email FROM agenda WHERE movil = '$tlfBuscado'", null)
        if (cursor.moveToFirst()) {
            val movil = cursor.getInt(0)
            val email = cursor.getString(1)
            etTlf.setText(movil.toString())
            etEmail.setText(email)
        }
        cursor.close()
        db.close()

        btnActualizar.setOnClickListener {
            val movil = etTlf.text.toString().toInt()
            val email = etEmail.text.toString()
            val db = miDBHelper.writableDatabase
            val orden = "UPDATE agenda SET email = '$email', movil = '$movil' WHERE movil = '$tlfBuscado'"
            db.execSQL(orden)
            db.close()
            Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
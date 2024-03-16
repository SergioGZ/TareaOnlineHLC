package com.example.sqlitetarea2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitetarea2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var miDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        miDBHelper = miSQLiteHelper(this)

        binding.btnGuardar.setOnClickListener {
            if (binding.etNick.text.isNotBlank() && binding.etMovil.text.isNotBlank() && binding.etApellido1.text.isNotBlank() && binding.etApellido2.text.isNotBlank() && binding.etNombre.text.isNotBlank() && binding.etEmail.text.isNotBlank()) {
                val nick = binding.etNick.text.toString()
                val movil = binding.etMovil.text.toString().toInt()
                val apellido1 = binding.etApellido1.text.toString()
                val apellido2 = binding.etApellido2.text.toString()
                val nombre = binding.etNombre.text.toString()
                val email = binding.etEmail.text.toString()

                miDBHelper.anyadirDatos(nick, movil, apellido1, apellido2, nombre, email)
                binding.etNick.text.clear()
                binding.etMovil.text.clear()
                binding.etApellido1.text.clear()
                binding.etApellido2.text.clear()
                binding.etNombre.text.clear()
                binding.etEmail.text.clear()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Faltan datos por rellenar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnConsultar.setOnClickListener {
            val intent = Intent(this, ConsultaActivity::class.java)
            startActivity(intent)
        }

        binding.btnConsultaNick.setOnClickListener {
            val intent = Intent(this, ConsultaNick::class.java)
            val editText = binding.etConsultaNick
            val texto = editText.text.toString()
            intent.putExtra("nick", texto)
            startActivity(intent)
        }

        binding.btnConsultaTlf.setOnClickListener {
            val intent = Intent(this, ConsultaTlf::class.java)
            val editText = binding.etConsultaTlf
            val texto = editText.text.toString()
            intent.putExtra("tlf", texto)
            startActivity(intent)
        }

        binding.btnEliminaNick.setOnClickListener {
            val nick = binding.etConsultaNick.text.toString()
            miDBHelper.borrarDatos(nick)
            binding.etConsultaNick.text.clear()
            Toast.makeText(this, "Datos eliminados", Toast.LENGTH_SHORT).show()
        }

        binding.btnEditarContacto.setOnClickListener {
            val intent = Intent(this, EditarContacto::class.java)
            val editText = binding.etConsultaTlf
            val texto = editText.text.toString()
            intent.putExtra("tlf", texto)
            startActivity(intent)
        }
    }
}
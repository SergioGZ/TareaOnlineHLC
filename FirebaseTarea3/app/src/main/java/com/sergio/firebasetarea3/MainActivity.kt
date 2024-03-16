package com.sergio.firebasetarea3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.sergio.firebasetarea3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConsultar.setOnClickListener {
            db.collection("agenda")
                .get()
                .addOnSuccessListener { result ->
                    binding.tvConsulta.text = ""
                    for (document in result) {
                        binding.tvConsulta.append("${document.id} => ${document.data}\n\n")
                    }
                }
                .addOnFailureListener { exception ->
                    binding.tvConsulta.text = "Error al consultar: ${exception.message}"
                }
        }

        binding.btnAnadir.setOnClickListener {
            val nick = binding.etNick.text.toString()
            val movil = binding.etMovil.text.toString().toInt()
            val apellido1 = binding.etApellido1.text.toString()
            val apellido2 = binding.etApellido2.text.toString()
            val nombre = binding.etNombre.text.toString()
            val email = binding.etEmail.text.toString()

            val datos = hashMapOf(
                "nick" to nick,
                "movil" to movil,
                "apellido1" to apellido1,
                "apellido2" to apellido2,
                "nombre" to nombre,
                "email" to email
            )

            db.collection("agenda")
                .document(binding.etID.text.toString())
                .set(datos)
                .addOnSuccessListener { _ ->
                        binding.tvConsulta.text = "Agregado correctamente"
                }
                .addOnFailureListener { _ ->
                    binding.tvConsulta.text = "Error al agregar"
                }
        }

        binding.btnListarContacto.setOnClickListener() {
            val id = binding.etID.text.toString()
            db.collection("agenda")
                .document(id)
                .get()
                .addOnSuccessListener { document ->
                    if (document.data != null) {
                        binding.tvConsulta.text = "${document.id} => ${document.data}"
                    } else {
                        binding.tvConsulta.text = "No existe el contacto"
                    }
                }
                .addOnFailureListener { exception ->
                    binding.tvConsulta.text = "Error al consultar: ${exception.message}"
                }
        }

        binding.btnEliminarContacto.setOnClickListener() {
            val id = binding.etID.text.toString()
            db.collection("agenda")
                .document(id)
                .delete()
                .addOnSuccessListener { _ ->
                    binding.tvConsulta.text = "Eliminado correctamente"
                }
                .addOnFailureListener { _ ->
                    binding.tvConsulta.text = "Error al eliminar"
                }
        }

        binding.btnEditarContacto.setOnClickListener() {
            val nick = binding.etNick.text.toString()
            val movil = binding.etMovil.text.toString().toInt()
            val apellido1 = binding.etApellido1.text.toString()
            val apellido2 = binding.etApellido2.text.toString()
            val nombre = binding.etNombre.text.toString()
            val email = binding.etEmail.text.toString()

            val datos = hashMapOf(
                "nick" to nick,
                "movil" to movil,
                "apellido1" to apellido1,
                "apellido2" to apellido2,
                "nombre" to nombre,
                "email" to email
            )

            db.collection("agenda")
                .document(binding.etID.text.toString())
                .set(datos)
                .addOnSuccessListener { _ ->
                    binding.tvConsulta.text = "Editado correctamente"
                }
                .addOnFailureListener { _ ->
                    binding.tvConsulta.text = "Error al editar"
                }
        }
    }
}
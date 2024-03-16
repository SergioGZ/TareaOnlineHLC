package com.example.sqlitetarea2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper(context: Context) : SQLiteOpenHelper(
    context,
    "miDB",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE agenda (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nick TEXT," +
                " movil NUMBER," +
                " apellido1 TEXT," +
                " apellido2 TEXT," +
                " nombre TEXT," +
                " email TEXT)"

        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS agenda"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun anyadirDatos(nick: String, movil: Number, apellido1: String, apellido2: String, nombre: String, email: String) {
        val db = writableDatabase
        val orden = "INSERT INTO agenda (nick, movil, apellido1, apellido2, nombre, email) " +
                "VALUES ('$nick', '$movil', '$apellido1', '$apellido2', '$nombre', '$email')"
        db.execSQL(orden)
        db.close()
    }

    fun borrarDatos(nick: String) {
        val db = writableDatabase
        val orden = "DELETE FROM agenda WHERE nick = '$nick'"
        db.execSQL(orden)
        db.close()
    }

}
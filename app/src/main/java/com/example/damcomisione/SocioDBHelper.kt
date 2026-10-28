package com.example.damcomisione

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class SocioDBHelper(context: Context) : SQLiteOpenHelper(context, "Deportivo.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE socios(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                dni TEXT NOT NULL,
                email TEXT NOT NULL
            )""".trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    fun agregarSocio(socio: Socio): Long {

        val db = writableDatabase

        //objeto que permite preparar pares clave valor
        val valores = ContentValues().apply {
            put("nombre", socio.nombre)
            put("dni", socio.dni)
            put("email", socio.email)
        }

        val resultado = db.insert("socios", null, valores)
        db.close()
        return resultado
    }

    fun obtenerSocios(): MutableList<Socio> {
        val lista = mutableListOf<Socio>()

        val db = readableDatabase

        val cursor = db.rawQuery("SELECT nombre, dni, email from socios ORDER BY id DESC", null)

        cursor.use {
            while (it.moveToNext()) {
                lista.add(
                    Socio(
                        nombre = it.getString(0),
                        dni = it.getString(1),
                        email = it.getString(2)
                    )
                )
            }
            db.close()
            return lista
        }
    }
}

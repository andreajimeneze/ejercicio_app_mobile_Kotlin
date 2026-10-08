package com.example.damcomisione

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val cardInscribirSocio = findViewById<LinearLayout>(R.id.cardInscribirSocio)

        cardInscribirSocio.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Inscribir nuevo socio")
                .setMessage("¿Estás seguro que desea inscribir al nuevo socio?")
                .setPositiveButton("Continuar") { _, _ ->
                    Toast.makeText(this, "Redirigiendo al formulario de inscripción", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, AltaSocioActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Cancelar", null)
                .show()
            }

        }
}

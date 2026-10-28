package com.example.damcomisione

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {
    private lateinit var rvSocios: RecyclerView
    private lateinit var txtSinSocios: TextView
    private lateinit var dbHelper: SocioDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        dbHelper = SocioDBHelper(this)

        val cardInscribirSocio = findViewById<LinearLayout>(R.id.cardInscribirSocio)

        rvSocios = findViewById(R.id.rvSocios)
        txtSinSocios = findViewById(R.id.txtSinSocios)

        //txtSinSocios.visibility = View.GONE
        rvSocios.layoutManager = LinearLayoutManager(this)

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

    override fun onResume() {
        super.onResume()
        cargarSocios()
    }

    private fun cargarSocios() {
        val socios = dbHelper.obtenerSocios()
        //val socios = Datos.socios.toList()
        rvSocios.adapter = SocioAdapter(socios)

        if(socios.isEmpty()) {
            txtSinSocios.visibility = View.VISIBLE
            rvSocios.visibility = View.GONE
        } else {
            txtSinSocios.visibility = View.GONE
            rvSocios.visibility = View.VISIBLE
        }
    }
}

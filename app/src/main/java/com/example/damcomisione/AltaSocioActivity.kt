package com.example.damcomisione

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AltaSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_alta_socio)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.alta_socio)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtDni = findViewById<EditText>(R.id.txtDni)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarSocio)

        btnGuardar.setOnClickListener {

            val nombre = txtNombre.text.toString().trim()
            val dni = txtDni.text.toString().trim()
            val email = txtEmail.text.toString().trim()

            if(nombre.isEmpty() || dni.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorias", Toast.LENGTH_SHORT).show()
            } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Ingrese un email válido", Toast.LENGTH_SHORT).show()
            } else {

                val socio = Socio(nombre = nombre, dni = dni, email = email)
                val dbHelper = SocioDBHelper(this)
                val resultado = dbHelper.agregarSocio(socio)

                if(resultado != -1L) {
                    Toast.makeText(this, "Socio creado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show()
                }
                //Datos.socios.add(socio)
                //Log.d("SOCIO", socio.toString())
                //finish()
            }

        }
    }
}
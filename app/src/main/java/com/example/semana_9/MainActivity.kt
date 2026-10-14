package com.example.semana_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semana_9.ui.theme.Semana_9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana_9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    formulario(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun formulario(modifier: Modifier = Modifier) {
    var nombrePersona by remember { mutableStateOf("") }
    var nombreRegistrado by remember { mutableStateOf("Ninguno") }
    var contadorPersonas by remember { mutableStateOf(0) }
    var personasmaximas by remember { mutableStateOf(3) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CONTROL DE AFORO ",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombrePersona,
            onValueChange = { nombrePersona = it },
            label = { Text("Nombre") },
            placeholder = { Text("Ingrese su nombre") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (contadorPersonas < 3) {
                nombreRegistrado = nombrePersona
                contadorPersonas += 1
                nombrePersona = ""
            }
        }) {
            Text("Registrar")
        }

        if (contadorPersonas == 3) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Aforo del local lleno")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Ultimo registrado: $nombreRegistrado")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Cantidad de personas: $contadorPersonas")

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                contadorPersonas -= 1
                if (contadorPersonas < 0) {
                    contadorPersonas = 0
                }
            }) {
                Text("Salida")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                contadorPersonas = 0
            }) {
                Text("Reiniciar ")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Semana_9Theme {
        formulario()
    }
}

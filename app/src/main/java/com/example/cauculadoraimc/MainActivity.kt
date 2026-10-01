package com.example.cauculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cauculadoraimc.ui.theme.CauculadoraImcTheme
import androidx.compose.material3.OutlinedTextFieldDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CauculadoraImcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun IMCScreen(name: String, modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }
    var peso by remember {
        mutableStateOf("")
    }
    var resultado by remember{
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.color_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "Logo App",
                modifier = Modifier
                    .size(80.dp)
                    .padding(vertical = 16.dp)

            )
            Text(
                text = "cauculadora IMC",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    contentColor = Color(0xFFF9F6F6)

                ),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Seus dados",
                        color = colorResource(R.color.color_app),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        verticalArrangement = Arrangement.spacedBy(25.dp)


                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = altura,
                            onValueChange = { altura = it },
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            placeholder = {
                                Text("altura EX: 175 ")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.DarkGray,
                                unfocusedPlaceholderColor = Color.DarkGray,
                                focusedBorderColor = colorResource(R.color.color_app),
                                unfocusedBorderColor = Color.Gray
                            ),
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = peso,
                            onValueChange = { peso = it },
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            placeholder = {
                                Text("seu peso")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.DarkGray,
                                unfocusedPlaceholderColor = Color.DarkGray,
                                focusedBorderColor = colorResource(R.color.color_app),
                                unfocusedBorderColor = Color.Gray
                            )
                        )

                        Button(
                            onClick = {
                                val alturaDouble = altura.toInt() / 100.0
                                val calculo = peso.toInt() / (alturaDouble * alturaDouble)

                                val classificacao = when {
                                    calculo < 18.5 -> "Abaixo do peso"
                                    calculo < 25 -> "Peso ideal"
                                    calculo < 30 -> "Levemente acima do peso"
                                    calculo < 35 -> "Obesidade grau I"
                                    calculo < 40 -> "Obesidade grau II"
                                    else -> "Obesidade grau III"
                                }

                                resultado = "%.1f  %s".format(calculo, classificacao)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.color_app)
                            )
                        ) {
                                Text(
                                    text = "CALCULAR",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = resultado,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }

fun mudarCor(imc: Double) {

//    return if (imc < 18.5 ||imc >25 || imc > 35 || imc > 40){
//
//    }
}

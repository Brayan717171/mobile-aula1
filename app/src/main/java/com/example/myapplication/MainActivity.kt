package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Limites de idade permitidos pelo enunciado
private const val IDADE_MINIMA = 0
private const val IDADE_MAXIMA = 180
private const val IDADE_MAIORIDADE = 18

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaIdade(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaIdade(modifier: Modifier = Modifier) {
    // estado mutavel guardando a idade atual
    var idade by remember { mutableIntStateOf(0) }

    val ehMaiorDeIdade = idade >= IDADE_MAIORIDADE

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Qual é a sua idade?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Instrução
        Text(
            text = "Aperte os botões para informar a sua idade",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Botões laterais + mostrador central
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão "-": impede ir abaixo de IDADE_MINIMA
            Button(
                onClick = {
                    if (idade > IDADE_MINIMA) {
                        idade--
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                modifier = Modifier.size(56.dp)
            ) {
                Text(text = "-", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            // Mostrador central da idade
            Text(
                text = idade.toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )

            // Botão "+": impede ir acima de IDADE_MAXIMA
            Button(
                onClick = {
                    if (idade < IDADE_MAXIMA) {
                        idade++
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784)),
                modifier = Modifier.size(56.dp)
            ) {
                Text(text = "+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }

        HorizontalDivider( // Dá o mesmo espaço que o Spacer porem posso colocar componentes
            modifier = Modifier.padding(vertical = 48.dp),
            thickness = 1.dp,
            color = Color.Gray
        )




        // Mensagem de feedback (maior/menor de idade)
        Text(
            text = if (ehMaiorDeIdade) "Você é MAIOR de idade" else "Você é MENOR de idade",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (ehMaiorDeIdade) Color(0xFF2E7D32) else Color(0xFFC62828),
            modifier = Modifier
                .background(
                    color = if (ehMaiorDeIdade) Color(0xFFC8E6C9) else Color(0xFFFFCDD2)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
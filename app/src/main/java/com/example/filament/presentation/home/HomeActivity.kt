package com.example.filament.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filament.R
import com.example.filament.core.theme.FilamentTheme
import com.example.filament.presentation.quiz.QuizScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilamentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    App()
                }
            }
        }
    }

    @Composable
    private fun App(){
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "home" // Initial screen
        ) {
            composable("home") { HomeContent(navController) }
            composable("quiz") { QuizScreen(navController) }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewHomeContent() {
        val navController = rememberNavController()
        HomeContent(navController)
    }


    @Composable
    private fun HomeContent(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color.Blue, Color.Cyan),
                        start = androidx.compose.ui.geometry.Offset(0f, 0f),
                        end = androidx.compose.ui.geometry.Offset(1500f, 1500f) // adjust as needed
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.icon_light_bulb),
                colorFilter = ColorFilter.tint(Color.Black),
                contentDescription = "logo",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp)
                    .width(100.dp)
                    .height(100.dp)
            )


            Text(
                text = "FILAMENT",
                fontSize = TextUnit(40F, TextUnitType.Sp),
                fontWeight = FontWeight(250),
                color = Color.Black,
                modifier = Modifier
                    .height(50.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(200.dp))

            Button(onClick = { navController.navigate("quiz") }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan // Change this to your desired color
            )) {
                Text(text = "Start Quiz", fontSize = TextUnit(18F, TextUnitType.Sp), color = Color.Black)
            }
        }
    }
}
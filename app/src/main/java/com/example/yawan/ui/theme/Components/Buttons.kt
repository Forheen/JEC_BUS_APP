package com.example.yawan.ui.theme.Components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeekdayButton(selected : Boolean, day : String, index : Int){
    var select = remember{ mutableStateOf(selected)}
    var days = remember{ mutableStateOf(day)}
    Button(

        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (select.value) Color.White else Color.Red),
        onClick = { select.value=!select.value
            println("Forheen ${select.value}")
            println("Forheen ${days.value}")

        },
        modifier = Modifier
            .width(55.dp)
            .height(40.dp),
        elevation =  ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp,


        )) {
        Text(text = "${days.value}", fontSize = 15.sp, fontFamily = FontFamily.Serif, color = if (select.value) Color.Black else Color.White)
    }
}
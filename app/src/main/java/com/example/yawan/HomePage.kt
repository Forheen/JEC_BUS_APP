package com.example.yawan

import android.widget.TextClock
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.util.*





//@Preview(showBackground = true)
@Composable
fun DefaultPreview(latitude : MutableState<Double>, longitude: MutableState<Double>, address: MutableState<String>) {

        Column ( modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())){
            heading()
            statusBar()
            calculationBar(latitude, longitude,address)
        }


}
@Composable
fun heading(){


    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(16.dp)) {



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "यावन्",
                    color = Color.Blue,

                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 40.sp
                )
            }

//            TextField(modifier = Modifier.padding(10.dp),
//                value = "dd/mm/yyyy", onValueChange = {text.value = it},
//                label = { Text("Enter date",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    fontStyle = FontStyle.Italic,
//                    fontFamily = FontFamily.Serif
//                )},

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val sdf = SimpleDateFormat("EEEE   dd MMM, yyyy")

                // on below line we are creating a variable for
                // current date and time and calling a simple
                // date format in it.
                val currentDate = remember {
                    mutableStateOf(sdf.format(Date()))}
                Text(
                    "${currentDate.value}", color = Color.Black,
                    modifier = Modifier.padding(4.dp),
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )

            }
//            var ticks by remember { mutableStateOf(0) }
//            LaunchedEffect(Unit) {
//                while(true) {
//                    delay(1000)
//                    ticks++
//                }
//            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                AndroidView(
                     // on below line we are initializing our text clock.
                    factory = { context ->

                        TextClock(context).apply {

                            // on below line we are setting 12 hour format.
                            format12Hour.let { this.format12Hour = "hh:mm:ss a" }
                            // on below line we are setting time zone.
                            timeZone?.let { this.timeZone = it }
                            // on below line we are setting text size.
                            textSize.let { this.textSize = 25f}
                            setTextColor(ContextCompat.getColor(context, R.color.black))

                        }
                    }
                    // on below line we are adding padding.

                )

            }
        }
    }
}

@Composable
fun statusBar(){
    Card(
        shape = RoundedCornerShape(8.dp),

        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text("Status:", modifier = Modifier.padding(end = 10.dp),fontFamily = FontFamily.Serif)
                    Text("Active",fontFamily = FontFamily.Serif)

                }
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                ){
                    Text("Route:", modifier = Modifier.padding(end = 10.dp),fontFamily = FontFamily.Serif)
                    Text("Jorhat to JEC",fontFamily = FontFamily.Serif)
                }
            }
            val currentStep = remember { mutableStateOf(1) }
            StepsProgressBar(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, end = 16.dp), numberOfSteps = 3, currentStep = currentStep.value)

        }
    }

}

@Composable
fun calculationBar(latitude: MutableState<Double>, longitude: MutableState<Double>, address: MutableState<String>){

    Column(modifier = Modifier.padding(end= 32.dp, start = 32.dp, top=16.dp, bottom = 16.dp)) {

                CallMap(latitude,longitude)

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Your location:", modifier = Modifier.padding(end = 10.dp))
            Text("${address.value}", fontWeight = FontWeight.Bold)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Distance from the nearest stop", modifier = Modifier.padding(end=8.dp))
            Text("GARMUR", fontWeight = FontWeight.Bold)

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("0", modifier = Modifier.padding(end=8.dp), fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp)
            Text("unit",modifier = Modifier.padding(end=16.dp),fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp)


        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("5", modifier = Modifier.padding(end=8.dp), fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color.Red,
                fontSize =15.sp)
            Text("min ",fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color.Red,

                fontSize =15.sp)
            Text("of Walking distance")
        }
    }
}

@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        for (step in 0..numberOfSteps) {
            if (step == numberOfSteps) {
Column(modifier = Modifier.weight(1F)) {
    val color = if (step < currentStep || step == currentStep) Color.Red else Color.LightGray
    val innerCircleColor = if (step < currentStep) Color.Red else Color.LightGray

    Box(modifier = Modifier.weight(1F)) {
        Canvas(modifier = Modifier
            .size(15.dp)
            .align(Alignment.CenterEnd)
            .border(
                shape = CircleShape,
                width = 2.dp,
                color = color
            ),
            onDraw = {
                drawCircle(color = innerCircleColor)
            }
        )
    }
    Text("$numberOfSteps")

}
            }else {


    Step(
        modifier = Modifier.weight(1F),
        isCompete = step < currentStep,
        isCurrent = step == currentStep,
        stepNumber = step,
    )


            }
        }
    }

}

@Composable
fun Step(modifier: Modifier = Modifier, isCompete: Boolean, isCurrent: Boolean, stepNumber : Int) {
    val color = if (isCompete || isCurrent) Color.Red else Color.LightGray
    val innerCircleColor = if (isCompete) Color.Red else Color.LightGray
Column(modifier = modifier) {


    Box(modifier = modifier) {

        //Line
        Divider(
            modifier = Modifier.align(Alignment.CenterStart),
            color = color,
            thickness = 2.dp
        )

        //Circle
        Canvas(modifier = Modifier
            .size(15.dp)
            .align(Alignment.CenterStart)
            .border(
                shape = CircleShape,
                width = 2.dp,
                color = color
            ),
            onDraw = {
                drawCircle(color = innerCircleColor)
            }
        )
        //Line
        Divider(
            modifier = Modifier.align(Alignment.CenterEnd),
            color = innerCircleColor,
            thickness = 2.dp
        )

    }
    Text("$stepNumber")

}
}

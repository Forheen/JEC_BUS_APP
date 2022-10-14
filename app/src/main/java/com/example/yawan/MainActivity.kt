package com.example.yawan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // from here main task start,here we have to code  //

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    DefaultPreview()
                }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

        Column ( modifier = Modifier
            .fillMaxSize()){
         heading()
            statusBar()
            calculationBar()
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
//moi type korim.. olop rua.
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Monday", color = Color.Black,
                    modifier = Modifier.padding(4.dp),
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )
                Text(
                    "0 Month, Year", color = Color.Black,
                    modifier = Modifier.padding(4.dp),

                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "12:00 am", color = Color.Black,
                    modifier = Modifier.padding(4.dp),
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
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
                Row(modifier = Modifier.fillMaxWidth(1f),
                ){
                    Text("Route:", modifier = Modifier.padding(end = 10.dp),fontFamily = FontFamily.Serif)
                    Text("Jorhat to JEC",fontFamily = FontFamily.Serif)
                }
            }
            val currentStep = remember { mutableStateOf(2) }
            StepsProgressBar(modifier = Modifier
                .fillMaxWidth()
                .padding(top=16.dp, bottom = 16.dp, end = 16.dp), numberOfSteps = 3, currentStep = currentStep.value)

        }
    }

}

@Composable
fun calculationBar(){

    Column(modifier = Modifier.padding(end= 32.dp, start = 32.dp, top=16.dp, bottom = 16.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Your location:", modifier = Modifier.padding(end = 10.dp))
            Text("Jorhat Engineering College", fontWeight = FontWeight.Bold)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Distance from the nearest stop", modifier = Modifier.padding(end=8.dp))
            Text("GARMUR", fontWeight = FontWeight.Bold)

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {
            Text("0", modifier = Modifier.padding(end=8.dp), fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp)
            Text("unit",modifier = Modifier.padding(end=16.dp),fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp)


        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,) {
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
            if (step == 0) {
                Box(modifier = Modifier.weight(1F)) {
                    Canvas(modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.CenterEnd)
                        .border(
                            shape = CircleShape,
                            width = 2.dp,
                            color = Color.Red
                        ),
                        onDraw = {
                            drawCircle(color = Color.Red)
                        }
                    )
                }
            }else {

                Step(
                    modifier = Modifier.weight(1F),
                    isCompete = step < currentStep,
                    isCurrent = step == currentStep
                )
            }
        }
    }
}

@Composable
fun Step(modifier: Modifier = Modifier, isCompete: Boolean, isCurrent: Boolean) {
    val color = if (isCompete || isCurrent) Color.Red else Color.LightGray
    val innerCircleColor = if (isCompete) Color.Red else Color.LightGray

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
}

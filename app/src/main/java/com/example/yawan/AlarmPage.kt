package com.example.yawan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yawan.ui.theme.Components.WeekdayButton


@Composable
fun AlarmScreen(
) {
    var selected = remember { mutableStateOf(arrayOf<Boolean>(true,true,true,true,true,true))}
    var days = remember { mutableStateOf(arrayOf<String>("M","T","W","Th","F","S"))}
    val repeat = remember { mutableStateOf(false)}
    var expanded by remember { mutableStateOf(false) }
    var expandDayList by remember { mutableStateOf(false) }
    val timeList = listOf("5 min", "10 min", "15 min", "20 min", "25 min", "30 min")
    val dayList = listOf("WeekDay", "Saturday")
    val busTimeList = listOf("7:15 AM","7:40 AM", "10:05 AM","10:20 AM", "12:40 PM","1:40 PM","4:10 PM","04:30 PM","05:00 PM", "06:00 PM")
    val selectedTime = remember { mutableStateOf(timeList[0]) }
    var selectedDay = remember { mutableStateOf(dayList[0]) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        //Alarm me Text

            Text(

                text = "Alarm Me",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(15.dp)
            )

        //Select Day Text


            Text(
                text = "Select Days",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily= FontFamily.SansSerif,
                        modifier = Modifier.padding(15.dp)

            )

        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            ) {

            for(index in 0..5){
            WeekdayButton(selected.value[index], days.value[index], index)
        }

        }
        //Spacer
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
        ) {
            Spacer(modifier = Modifier.fillMaxSize())
        }
        // Repeat Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.47f)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (!repeat.value) Color.Red else Color.White),
                    onClick = { repeat.value = false  },
                    modifier = Modifier
                        .fillMaxSize(),
                    elevation =  ButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 15.dp,
                        disabledElevation = 0.dp

                    )) {
                    Text(text = "Repeat Once", fontSize = 13.sp,color = if (!repeat.value) Color.White else Color.Black)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (repeat.value) Color.Red else Color.White),
                    onClick = { repeat.value = true  },
                    modifier = Modifier
                        .fillMaxSize(),
                    elevation =  ButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 15.dp,
                        disabledElevation = 0.dp

                    )) {
                    Text(text = "Repeat Every Week",  fontSize = 13.sp,color = if (repeat.value) Color.White else Color.Black)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        //Alert Before Area
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp)


        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                //Alert Text
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Alert Before", fontSize = 20.sp, color= Color.White)
                }
                //DropDownMenu
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colors.primary)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { expanded = !expanded },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.60f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(text = selectedTime.value, fontSize = 18.sp, color= Color.White)
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(6.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                icon,
                                contentDescription = " ",
                                Modifier.clickable { expanded = !expanded })
                        }

                        DropdownMenu(expanded = expanded, modifier = Modifier.fillMaxWidth(0.4f),onDismissRequest = { expanded = false }) {
                            timeList.forEach {
                                DropdownMenuItem(onClick = {
                                    selectedTime.value = it
                                    expanded = false
                                }) {
                                    Text(text = it)
                                }
                            }
                        }
                    }
                }

            }
        }
        //Week day or saturday selecting DropDown menu
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { expandDayList = !expandDayList },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier = Modifier
                .fillMaxWidth(0.02f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                DropdownMenu(expanded = expandDayList, onDismissRequest = { expandDayList = false }) {
                    dayList.forEach{
                        DropdownMenuItem(onClick = {
                            selectedDay.value = it
                            expandDayList = false
                        }
                        ) {
                            Text(text = it)
                        }
                    }
                }
            }
            Text(text = selectedDay.value, fontSize = 24.sp)
        }
        //Routes Showing and Selecting area
        busTimeList.forEach{
            ListTime(time = it)
        }
        //Save Button
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(110.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primaryVariant),
                onClick = {  },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.4f),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "Done", fontSize = 20.sp)
            }
        }
    }
}


@Composable
fun ListTime(time: String){

    var expanded by remember {
        mutableStateOf(false)
    }
    val listOfStoppage = listOf("JEC","Lahoty","Gar-Ali","A.T. Road","Baruah Chariali", "Doss & co", "Head Post Office","Gitarthi Tini Ali")
    val selectedPickUp = remember {
        mutableStateOf("Select your Pickup")
    }
    var selected by remember {
        mutableStateOf(false)
    }


        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp)


        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .clickable { expanded = !expanded }
                .background(if (selected) MaterialTheme.colors.primary else Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = time,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.Serif,
                        color = if (!selected) Color.Black else Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.675f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedPickUp.value,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.Serif,
                        color = if (!selected) Color.Black else Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.02f)
                        .fillMaxHeight(),
                ) {
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        listOfStoppage.forEach {
                            DropdownMenuItem(onClick = {
                                selectedPickUp.value = it
                                selected = true
                                expanded = false
                            }
                            ) {
                                Text(
                                    text = it, fontSize = 13.sp,
                                    fontFamily = FontFamily.Serif
                                )
                            }
                        }
                    }
                }

            }
        }

}




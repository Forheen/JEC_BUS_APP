package com.example.yawan

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll as verticalScroll



@Composable
fun AlarmScreen(
) {
    val selectedM = remember { mutableStateOf(true) }
    val selectedT = remember { mutableStateOf(true) }
    val selectedW = remember { mutableStateOf(true) }
    val selectedTh = remember { mutableStateOf(true) }
    val selectedF = remember { mutableStateOf(true) }
    val selectedS = remember { mutableStateOf(true) }
    val repeat = remember { mutableStateOf(false)}
    var expanded by remember { mutableStateOf(false) }
    var expandDayList by remember { mutableStateOf(false) }
    val timeList = listOf("5 min", "10 min", "15 min", "20 min", "25 min", "30 min")
    val dayList = listOf("WeekDay", "Saturday")
    val selectedTime = remember { mutableStateOf(timeList[0]) }
    var selectedDay = remember { mutableStateOf(dayList[0]) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ){
        //Alarm me Text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text(
                text = "Alarm Me",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
        //Select Day Text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text(
                text = "Select Days",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedM.value) Color.White else Color.Red),
                onClick = { selectedM.value = !selectedM.value  },
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "M", fontSize = 24.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedT.value) Color.White else Color.Red),
                onClick = { selectedT.value = !selectedT.value  },
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "T", fontSize = 24.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedW.value) Color.White else Color.Red),
                onClick = { selectedW.value = !selectedW.value  },
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "W", fontSize = 24.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedTh.value) Color.White else Color.Red),
                onClick = { selectedTh.value = !selectedTh.value  },
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "T", fontSize = 24.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedF.value) Color.White else Color.Red),
                onClick = { selectedF.value = !selectedF.value  },
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "F", fontSize = 24.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedS.value) Color.White else Color.Red),
                onClick = { selectedS.value = !selectedS.value  },
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp

                )) {
                Text(text = "S", fontSize = 24.sp)
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
                .padding(5.dp)
                .height(70.dp),
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
                    Text(text = "Repeat Once", fontSize = 20.sp)
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
                    Text(text = "Repeat Every Week", fontSize = 20.sp)
                }
            }
        }
        //Spacer
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
        ) {
            Spacer(modifier = Modifier.fillMaxSize())
        }
        //Alert Before Area
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
        ) {
            //Alert Text
            Column(modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Alert Before", fontSize = 20.sp)
            }
            //DropDownMenu
            Column(modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight()
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .clickable { expanded = !expanded },
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth(0.60f)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = selectedTime.value, fontSize = 20.sp)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(6.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(icon, contentDescription =" ", Modifier.clickable { expanded = !expanded } )
                    }

                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false}) {
                        timeList.forEach{
                            DropdownMenuItem(onClick = {
                                selectedTime.value=it
                                expanded=false
                            }) {
                                Text(text = it)
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
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
        ) {
            RecyclerView()
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
                    backgroundColor =  Color.Gray),
                onClick = {  },
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(0.5f),
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

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(10.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .clickable { expanded = !expanded }
            .background(if (selected) Color.Cyan else Color.White)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.25f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = time, fontSize = 24.sp)
            }
            Column(modifier = Modifier
                .fillMaxWidth(0.675f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = selectedPickUp.value, fontSize = 20.sp)
            }
            Box(modifier = Modifier
                .fillMaxWidth(0.02f)
                .fillMaxHeight(),
            ) {
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    listOfStoppage.forEach{
                        DropdownMenuItem(onClick = {
                            selectedPickUp.value = it
                            selected = true
                            expanded = false
                        }
                        ) {
                            Text(text = it)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun RecyclerView(){
    val timeList = listOf("7:15 AM","7:40 AM", "10:05 AM","10:20 AM", "12:40 PM","1:40 PM","4:10 PM","04:30 PM","05:00 PM", "06:00 PM")
    LazyColumn(Modifier.padding(vertical = 4.dp)){
        itemsIndexed(timeList){
                _,time->
            ListTime(time = time)
        }
    }
}


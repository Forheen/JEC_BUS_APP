package com.example.yawan
import android.os.Build
import android.os.Bundle
import android.widget.TextClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // from here main task start,here we have to code  //

                // A surface container using the 'background' color from the theme
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()


                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = {

                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {

                                        scope.launch {
                                            scaffoldState.drawerState.open()
                                        }
                                    },
                                ) {
                                    Icon(
                                        Icons.Rounded.Menu,
                                        contentDescription = ""
                                    )
                                }
                            })
                    },


                    drawerContent = { DrawerView() },

                    bottomBar = {}//add bottom navigation content

                ) {
                  DefaultPreview()


                }
            }


    }
}
@Composable
fun getTime(){
   AndroidView(modifier = Modifier
       .fillMaxWidth()
       .height(50.dp)
       .background(Color.Red),
                    // on below line we are initializing our text clock.
                    factory = { context ->
                        TextClock(context).apply {
                            // on below line we are setting 12 hour format.
                            format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                            // on below line we are setting time zone.
                            timeZone?.let { this.timeZone = it }
                            // on below line we are setting text size.
                            textSize.let { this.textSize = 30f}
                        }
                    }
                    // on below line we are adding padding.

                )
}
@Composable
fun DrawerView() {
    val language = listOf("Home ", "Alarm Me", "Driver Login")
    LazyColumn {

        items(language.size){index->

            AddDrawerContentView(title = language[index], selected = if (index==0)true else false)
        }
        item {
            AddDrawerHeader(title = "Settings")
        }


    }

}
@Composable
fun AddDrawerContentView(title: String,  selected: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {}
            .padding(horizontal = 16.dp, vertical = 12.dp),


        ) {

        if (title.isNotEmpty()) {
            if (selected)
                Text(text = title, modifier = Modifier.weight(1f),
                    color = Color.Black,style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                ))
            else
                Text(text = title, modifier = Modifier.weight(1f),
                    fontSize = 12.sp)
        }

    }
}
@Composable
fun AddDrawerHeader(
    title: String,
    titleColor: Color = Color.Black,
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = Color.Gray),


        ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor
            ),
            modifier = Modifier.padding(14.dp)
        )

    }
}
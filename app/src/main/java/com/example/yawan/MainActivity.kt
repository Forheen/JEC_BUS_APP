package com.example.yawan
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // from here main task start,here we have to code  //

                // A surface container using the 'background' color from the theme
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            var isOpenDrawer =remember { mutableStateOf(true)}


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

                    if(selectedindex.value==0){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        DefaultPreview()
                    }else if(selectedindex.value==1){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        AlarmScreen()
                    }else if(selectedindex.value==2){
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        SplashScreen()
                    }


                }
            }


    }

}
var selectedindex= mutableStateOf(0)
@Composable
fun DrawerView() {

    val language = listOf("Home ", "Alarm Me", "Driver Login")
    LazyColumn {
        item {
            AddDrawerHeader(title = "Settings")
        }
        items(language.size){index->
            AddDrawerContentView(title = language[index], index)
        }



    }

}
@Composable
fun AddDrawerContentView(title: String, index : Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {

                selectedindex.value = index
            }
            .padding(horizontal = 16.dp, vertical = 12.dp)
            ,


        ) {

        if (title.isNotEmpty()) {
            Text(
                text = title, modifier = Modifier.weight(1f),
                color = Color.Black, style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
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
            .padding(10.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = Color.Gray),


        ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = titleColor
            ),
            modifier = Modifier.padding(14.dp)
        )

    }
}
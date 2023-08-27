package com.example.riverstone.navigation

 import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.riverstone.presentation.MainViewModel
import com.example.riverstone.presentation.ui.theme.PostsKtorClientTheme


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){
        composable( route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route+"/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "hello"
                    nullable  = true
                }
            )
        ){entry->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable
fun MainScreen(navController:NavController ){
    val viewModel = hiltViewModel<MainViewModel>()
    val state = viewModel.state.value
    PostsKtorClientTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.posts != null) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.posts) {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.DetailScreen.withArgs(it.body))
                                }
                                .padding(horizontal = 7.dp, vertical = 2.2.dp)
                                .fillMaxWidth(),
                            backgroundColor = Color.White
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(1.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(50.dp),
                                ) {
                                    val painter =
                                        rememberImagePainter(data = "https://yt3.googleusercontent.com/ytc/AOPolaQHa4sJblMTBun6QcMeMok6jXtawy5qYSRwF_-qTQ=s176-c-k-c0x00ffffff-no-rj",
                                            builder = {
                                                crossfade(1000)
                                                transformations(
                                                    CircleCropTransformation(),
                                                )
                                            }
                                        )
                                    painter.state
                                    Image(
                                        painter = painter,
                                        contentDescription = "logo image"
                                    )
                                }
                                Spacer(modifier = Modifier.width(3.dp))

                                Column(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(1.dp)
                                    ) {

                                        Text(
                                            text = "Title : ",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                        Spacer(modifier = Modifier.width(1.dp))

                                        Text(
                                            text = it.title,
                                            modifier = Modifier.fillMaxWidth(),
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    }
                                    Spacer(modifier = Modifier.padding(2.dp))

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(1.dp)
                                    ) {
                                        Text(
                                            text = "Body : ",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                        Spacer(modifier = Modifier.width(1.dp))

                                        Text(
                                            text = it.body,
                                            modifier = Modifier.fillMaxWidth(),
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                if (state.loading) {
                    CircularProgressIndicator()
                } else {
                    state.error?.let { Text(text = it) }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(name: String?){
    PostsKtorClientTheme {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopAppBar(
                title = {
                    Text(text = "Post Details")
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(4.dp),
                backgroundColor = Color.White,
                elevation = 14.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp),
                    ) {
                        val painter =
                            rememberImagePainter(data = "https://img.freepik.com/premium-photo/very-cute-kid-caracter-animation-pixar-style_950002-74168.jpg",
                                builder = {
                                    crossfade(1000)
                                    transformations(
                                        CircleCropTransformation(),
                                    )
                                }
                            )
                        painter.state
                        Image(
                            painter = painter,
                            contentDescription = "logo image"
                        )
                    }

                    Spacer(modifier = Modifier.height(7.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp)
                    ) {
                        Text(
                            text = "Body : ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.width(1.dp))

                        Text(
                            text = name.toString(),
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }

        }
    }
}
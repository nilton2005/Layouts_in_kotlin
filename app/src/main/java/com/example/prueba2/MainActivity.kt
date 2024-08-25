package com.example.prueba2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba2.ui.theme.Prueba2Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prueba2Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GreetingPrime()
                }


                //MyScreen()
//                   SearchableDropdown()
//                   MyScreen()
                //Exercice2(modifier = Modifier.fillMaxSize())
//                   Exercice3(
//                       modifier = Modifier
//                           .fillMaxSize()
//                           .padding(100.dp)
//                   )
                //ConditionalFocusExercise()
                //Exercice3(modifier = Modifier)

            }
        }
    }
}

@Composable
fun GreetingPrime(){
    Column {
        GreetingTitle()
        GreetingBarSearch()
        GreetingMarketing()
        GreetingCategory()
        GreetingCounter(
            categorias = listOf(
                Category("Bugia", image = R.drawable.sparkplug),
                Category("Exterior", image = R.drawable.car_dor),
                Category("Audio/Video", image = R.drawable.multimedia),
                Category("Llanta", image = R.drawable.tire),
                Category("Parabrisas", image = R.drawable.windscreen),
                Category("Focos/Luces", image = R.drawable.car_light),
            )
        )
        ProductRating(images = mutableListOf(R.drawable.filtro, R.drawable.pastillas, R.drawable.alternador))

    }

}

@Composable
fun GreetingTitle(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Text(
            text = stringResource(id = R.string.name_app),
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,

            )

    }
}

@Composable
fun GreetingBarSearch() {
    var contentMutable by remember {
        mutableStateOf(
            ""
        )
    }

    OutlinedTextField(
        value = contentMutable,
        onValueChange = { contentMutable = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon" // Correct way to provide content description
            )
        },
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun GreetingMarketing(){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            Arrangement.SpaceAround

        ) {
            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = "Carros",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )

            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Motos",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )

            }
        }
    }
}
@Composable
fun GreetingCategory() {
    Column {
        Row {
            TextButton(
                onClick = { /*TODO*/ },

                ) {
                Text(
                    text = stringResource(id =R.string.category) ,
                    color = Color.Red,
                    textDecoration = TextDecoration.Underline,
                )

            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.brand))

            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.browse))
            }
        }
    }
}

@Composable
fun GreetingCounter(categorias: List<Category>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(categorias){
                categoria -> ProductCard(category = categoria)
        }

    }

}
@Composable
fun ProductCard(category: Category){
    Card (
        modifier = Modifier
            .size(100.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(50.dp)

            )
            Text(text = category.name,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()

            )

        }

    }

}
data class Category(val name: String, val image: Int)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductRating(images: List<Int>) { // Assuming images are represented as resource IDs
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f, pageCount = { images.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color(0xFF4E0404))
    ) {
        Text(
            text = stringResource(
            id = R.string.title2),

            fontSize = 30.sp,
            // Esto de abajo es lo pero que puedes hacer :(
            //fontFamily = FontFamily(),
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            //add color  hexadecimal to text
            color = Color(0xFFFFEB3B),
            modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp)
                .background(Color(0xFF4E0404))
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(start = 55.dp)





        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.CenterHorizontally)
                //.border(4.dp, Color.Black)
        ) { page ->

            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Product Image",
                modifier = Modifier
                    .padding(start = 100.dp)
                    .border(2.dp, Color.Black)
            )
        }

        // Auto-scroll logic (we'll add this later)
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000) // Delay for 3 seconds

                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

}


@Composable
fun SearchableList(
    modifier: Modifier
) {
    val items = listOf("Apple", "Banana", "Cherry", "Date", "Fig", "Grape", "Kiwi")

    var searchQuery by remember { mutableStateOf("") }
    var showLazyColumn by remember { mutableStateOf(false) }

    val filteredItems = items.filter {
        it.contains(searchQuery, ignoreCase = true)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp),
    ) {

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )


        if (showLazyColumn) {
            LazyColumn {
                items(filteredItems) { item ->
                    Text(text = item, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Prueba2Theme {
        GreetingPrime()

    }

}
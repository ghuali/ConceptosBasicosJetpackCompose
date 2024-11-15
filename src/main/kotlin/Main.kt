import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun aplicacion(listagayming : List<String> = listOf(
    "Soy una lista gamer",
    "Soy una lista muy gaymer",
    "Soy una lista poco gaymer",
    "Soy una lista que se llama Ang"
)) {
    var enseñar by remember { mutableStateOf("Show more") }



    Column (modifier = Modifier.padding(5.dp)
                                .fillMaxWidth()){
        Row {
            for (I in listagayming) {
                Text(I)
                Button(
                    onClick = {
                    Text("show less")}
                ) {
                    Text(enseñar)

                }
            }
        }
    }
}


//@Composable
//fun App() {
//    var estado by remember { mutableStateOf(true) }
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("wassup bb girl")
//        Button(onClick = {
//            if (estado) {

//            }
//        }) {
//            Text("Comenzamos")
//        }
//    }





fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        aplicacion()
    }
}

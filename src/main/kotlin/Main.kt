import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun App() {
    MaterialTheme {
        var mostrarInicio = remember { mutableStateOf(true) }
        if (mostrarInicio.value) {
            Inicio(mostrarInicio)
        } else {
            Pantalla2()
        }

    }
}



@Composable
fun Inicio(mostrarInicio: MutableState<Boolean>) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = "Welcome to the Basics Codelabs!"
        )
        Button(onClick = {
            mostrarInicio.value = false
        }) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun Pantalla2(modifier: Modifier = Modifier) {

    val textos: MutableList<String> = mutableListOf(
        "te odio Andrei",
        "te amo Samu",
        "te quiero Alvaro",
        "AMO A ADAY(no soy gay)"
    )
    val names: List<String> = List(1000) { "$it" }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(names) { name ->
            Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                Fila(name, modifier = Modifier)
            }
        }
    }
}


@Composable
fun Fila(texto:String, modifier: Modifier = Modifier){
    var expand = rememberSaveable { mutableStateOf(false) }
    val expandPadding by animateDpAsState(
        if (expand.value) 70.dp else 0.dp,
    )
    val currentPadding = expandPadding.coerceAtLeast(0.dp)
    Column (modifier = Modifier
        .background(Color.Yellow)
        .fillMaxWidth()
        .padding(15.dp)
        .animateContentSize (
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
    ){
        Row {
            Text(
                text = texto,
                color = Color.Magenta,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { expand.value = !expand.value },
                modifier = Modifier.padding(bottom = currentPadding)
            ) {
                Icon(
                    imageVector = if (expand.value) Filled.KeyboardArrowDown else Filled.KeyboardArrowUp,
                    contentDescription =
                        if (expand.value) {
                            "Mostar menos"
                        } else {
                            "Mostrar más"
                        }
                )
            }
        }
        Row {
            if (expand.value) {
                Text(
                    text = ("Texto aleatorio1, " +
                            "Texto aleatorio2. ").repeat(4),
                )
            }
        }


    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
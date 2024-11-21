import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showInicio by remember { mutableStateOf(true) }
        if (showInicio) {
            Inicio{showInicio = false}
        } else {
            SegundaPantalla()
        }
    }
}

@Composable
fun Inicio(changeShowInicio: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("gaypantalla de inicio")
        Button(
            onClick = changeShowInicio,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff9b88f6))
        ){
            Text(
                text = "sigue",
                color = Color.White
            )
        }
    }

}

@Composable
fun SegundaPantalla(){
    val textos: MutableList<String> = mutableListOf(
        "te odio Andrei",
        "te amo Samu",
        "te quiero Alvaro",
        "AMO A ADAY(no soy gay)"
    )

    Column {
        textos.forEach{ texto ->
            Tarjeta(texto)
        }
    }
}

@Composable
fun Tarjeta(texto: String = ""){
    var expanded by rememberSaveable { mutableStateOf(0.dp) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val animationPadding by
        animateDpAsState(
            if (isExpanded) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

    val currentPadding = animationPadding.coerceAtLeast(0.dp)

    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        backgroundColor = Color(0xfff5e6cc),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = expanded)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = texto
            )
            Button(
                onClick = {
                    if (isExpanded) {
                        expanded = 0.dp
                        isExpanded = !isExpanded
                    } else{
                        expanded = 120.dp
                        isExpanded = !isExpanded
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffc8f4d6))
            ){
                Text(
                    text = (if (isExpanded) "Show less" else "Show more"),
                    color = Color.White
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

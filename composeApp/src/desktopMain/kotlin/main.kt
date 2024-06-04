import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension

fun main() {
    return application {
        Window(
            onCloseRequest = ::exitApplication,
            resizable = false,
            state = rememberWindowState(
                size = DpSize(Dp(800f), Dp(600f))
            ),
            title = "Digital Certificate Extractor"
        ) {
            window.minimumSize = Dimension(800,600)
            App()
        }
    }
}
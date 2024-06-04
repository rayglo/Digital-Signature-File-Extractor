import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    return application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "P7M Extractor",
        ) {
            App()
        }
    }
}
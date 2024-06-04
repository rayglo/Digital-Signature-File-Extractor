import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import extractor.P7MExtractor
import java.io.File

fun main() {
    /*val file = File("test.pdf.p7m.p7m")
    val extractor = P7MExtractor()
    val extractedFile = extractor.extract(file)*/

    return application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Digital Certificate Extractor",
        ) {
            App()
        }
    }
}
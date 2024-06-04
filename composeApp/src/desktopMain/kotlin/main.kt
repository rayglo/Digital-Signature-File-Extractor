import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import de.comahe.i18n4k.Locale
import de.comahe.i18n4k.config.I18n4kConfigDefault
import de.comahe.i18n4k.i18n4k
import java.awt.Dimension

fun main() {

    var locale = Settings().getLocale()
    if (locale == null) {
        locale = "en"
        Settings().setLocale(locale)
    }

    val i18n4kConfig = I18n4kConfigDefault()
    i18n4k = i18n4kConfig
    i18n4kConfig.locale = Locale(locale)

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
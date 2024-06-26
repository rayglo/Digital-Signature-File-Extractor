package composables

import MyMessages
import Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.comahe.i18n4k.Locale
import de.comahe.i18n4k.config.I18n4kConfigDefault
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.resources.painterResource
import digitalsignaturefileextractor.composeapp.generated.resources.Res
import digitalsignaturefileextractor.composeapp.generated.resources.github

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsTab(onLocaleChange: (Locale) -> Unit = { _ -> }) {
    val options = listOf(("en" to "\uD83C\uDDEC\uD83C\uDDE7 English"), ("it" to "\uD83C\uDDEE\uD83C\uDDF9 Italian"))
    val settings = Settings()
    var isLocaleDropdownOpen by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = MyMessages.settings.toString(),
            textDecoration = Underline,
            fontSize = 24.sp
        )


        Text(
            modifier = Modifier.fillMaxWidth().offset(x = 120.dp, y = 0.dp),
            textAlign = TextAlign.Left,
            text = MyMessages.language.toString(),
            fontSize = 20.sp
        )

        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
            ExposedDropdownMenuBox(
                expanded = isLocaleDropdownOpen,
                onExpandedChange = { isLocaleDropdownOpen = it },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    value = options.find { it.first == i18n4k.locale.toString() }?.second.orEmpty(),
                    onValueChange = {},
                    singleLine = true,
                    label = { Text(MyMessages.language.toString()) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isLocaleDropdownOpen) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                ExposedDropdownMenu(
                    expanded = isLocaleDropdownOpen,
                    onDismissRequest = { isLocaleDropdownOpen = false },
                ) {
                    options.forEach { (value, label) ->
                        DropdownMenuItem(
                            onClick = {
                                isLocaleDropdownOpen = false
                                settings.setLocale(value)
                                val newConfig = I18n4kConfigDefault().apply {
                                    locale = Locale(value)
                                }
                                i18n4k = newConfig
                                onLocaleChange(Locale(value))
                            }
                        ) {
                            Text(label)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            IconButton(
                onClick = {
                val url = "https://bit.ly/4c8EQIj"
                java.awt.Desktop.getDesktop().browse(java.net.URI(url))
            }){
                Icon(
                    painter = painterResource(Res.drawable.github),
                    contentDescription = "GitHub",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.primary
                )
            }
        }

    }
}
package com.typ.citypulse.ui.screens.reporting

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.spr.jetpack_loading.components.indicators.LineSpinFadeLoaderIndicator
import com.typ.citypulse.R
import com.typ.citypulse.data.Location
import com.typ.citypulse.ui.utils.PreviewContainer
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.delay

class CRLocationSelectorScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        var isLocating by remember { mutableStateOf(true) }

        val location = remember {
            Location(
                city = "Zagazig",
                state = "Sharkia",
                latitude = 30.12317625673,
                longitude = 31.2526154235
            )
        }

        AnimatedContent(isLocating) { locating ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (locating) {
                    LineSpinFadeLoaderIndicator(
                        color = CupertinoTheme.colorScheme.label.copy(1.0f),
                    )

                    Spacer(Modifier.height(32.dp))

                    CupertinoText(
                        text = stringResource(R.string.locating_gps),
                        style = CupertinoTheme.typography.title1,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                    )

                    Spacer(Modifier.height(0.dp))

                    CupertinoText(
                        text = stringResource(R.string.msg_locating_gps),
                        color = CupertinoTheme.colorScheme.secondaryLabel,
                        style = CupertinoTheme.typography.title2,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                    )

                    Spacer(Modifier.height(16.dp))

                    CupertinoText(
                        text = stringResource(R.string.note_locating_gps),
                        color = CupertinoTheme.colorScheme.secondaryLabel,
                        style = CupertinoTheme.typography.title3,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    LaunchedEffect(Unit) {
                        delay(5000L)
                        isLocating = false
                    }
                } else {
                    LocationField(label = "City", value = location.city)
                    LocationField(label = "Country", value = location.state)
                    LocationField(label = "Latitude", value = location.latitude.toString())
                    LocationField(label = "Longitude", value = location.longitude.toString())

                    Spacer(Modifier.height(24.dp))

                    CupertinoButton(
                        onClick = {
//                            navigator?.push()
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .height(48.dp)
                    ) {
                        CupertinoText("Submit Report to Authorities")
                    }
                }
            }
        }
    }

    @Composable
    private fun LocationField(label: String, value: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = CupertinoTheme.colorScheme.secondarySystemBackground,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    vertical = 12.dp,
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CupertinoText(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = CupertinoTheme.colorScheme.secondaryLabel
            )
            CupertinoText(
                text = value,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = CupertinoTheme.colorScheme.label
            )
        }
    }

}

@Preview
@Composable
private fun CRCaptureSceneScreenPreview() {
    PreviewContainer {
        CRLocationSelectorScreen().Content()
    }
}
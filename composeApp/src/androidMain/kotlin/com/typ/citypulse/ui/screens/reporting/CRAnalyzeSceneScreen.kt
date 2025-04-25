package com.typ.citypulse.ui.screens.reporting

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import cafe.adriel.voyager.navigator.LocalNavigatorSaver
import com.spr.jetpack_loading.components.indicators.LineSpinFadeLoaderIndicator
import com.typ.citypulse.R
import com.typ.citypulse.ui.utils.PreviewContainer
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.delay

class CRAnalyzeSceneScreen : Screen {

    @Composable
    override fun Content() {
        // * Runtime * //
        val geminiEngine = remember { "TODO" }
        val coroutineScope = rememberCoroutineScope()
        var isAnalyzing by remember { mutableStateOf(true) }
        val navigator = LocalNavigator.current

        // * UI * //
        AnimatedContent(
            targetState = isAnalyzing,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) { analyzing ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ) {
                if (analyzing) {
                    LineSpinFadeLoaderIndicator(
                        color = CupertinoTheme.colorScheme.label.copy(1.0f),
                    )

                    Spacer(Modifier.height(32.dp))

                    CupertinoText(
                        text = stringResource(R.string.analyzing_scene),
                        style = CupertinoTheme.typography.title1,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                    )

                    Spacer(Modifier.height(0.dp))

                    CupertinoText(
                        text = stringResource(R.string.msg_analyze_scene),
                        color = CupertinoTheme.colorScheme.secondaryLabel,
                        style = CupertinoTheme.typography.title2,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                    )

                    LaunchedEffect(Unit) {
                        delay(5000L)
                        isAnalyzing = false
                    }
                } else {

                    CupertinoText(
                        text = stringResource(R.string.analyze_scene_result),
                        style = CupertinoTheme.typography.title1,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                    )

                    Spacer(Modifier.height(0.dp))

                    CupertinoText(
                        text = stringResource(R.string.mock_analyze_scene_response),
                        color = CupertinoTheme.colorScheme.secondaryLabel,
                        style = CupertinoTheme.typography.title2,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                    )

                    CupertinoButton(
                        onClick = {
                            navigator?.push(CRLocationSelectorScreen())
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .height(48.dp)
                    ) {
                        CupertinoText("Proceed to Location")
                    }
                }


            }
        }
    }
}

@Preview
@Composable
private fun CRAnalyzeSceneScreenPreview() {
    PreviewContainer {
        CRAnalyzeSceneScreen().Content()
    }
}
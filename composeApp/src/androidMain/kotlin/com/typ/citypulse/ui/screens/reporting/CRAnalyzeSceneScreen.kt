package com.typ.citypulse.ui.screens.reporting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import com.spr.jetpack_loading.components.indicators.BallScaleMultipleIndicator
import com.spr.jetpack_loading.components.indicators.LineSpinFadeLoaderIndicator
import com.typ.citypulse.R
import com.typ.citypulse.ui.utils.PreviewContainer
import com.typ.citypulse.ui.utils.previewContainerPaddings
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

class CRAnalyzeSceneScreen : Screen {

    @Composable
    override fun Content() {
        // * Runtime * //
        val geminiEngine = remember { "TODO" }
        var isAnalyzing by remember { mutableStateOf(true) }

        // * UI * //
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            LineSpinFadeLoaderIndicator(
                color= CupertinoTheme.colorScheme.label.copy(1.0f),
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
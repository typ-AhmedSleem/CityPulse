package com.typ.citypulse.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.typ.citypulse.R
import com.typ.citypulse.ui.screens.reporting.CRCaptureSceneScreen
import com.typ.citypulse.ui.utils.PreviewContainer
import com.typ.citypulse.ui.utils.previewContainerPaddings
import io.github.alexzhirkevich.cupertino.CupertinoButtonDefaults
import io.github.alexzhirkevich.cupertino.CupertinoHorizontalDivider
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoIconButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.Camera
import io.github.alexzhirkevich.cupertino.icons.outlined.CameraViewfinder
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

class UserRoleHomeScreen : BaseHomeScreen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .previewContainerPaddings(8),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CupertinoText(
                text = stringResource(R.string.recent_reports),
                style = CupertinoTheme.typography.title1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
            )

            MostRecentReportView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {}

            CupertinoHorizontalDivider()
            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterVertically)
            ) {
                CupertinoText(
                    text = stringResource(R.string.click_btn_to_create_report),
                    style = CupertinoTheme.typography.title2,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                )

                CreateReportButton {
                    navigator?.push(CRCaptureSceneScreen())
                }

            }

        }
    }

    @Composable
    private fun MostRecentReportView(
        modifier: Modifier,
        onClick: (/*todo: add report as argument*/) -> Unit
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(CupertinoTheme.colorScheme.secondarySystemBackground)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            CupertinoText("The most recent report you\ncreated will be displayed here!")
        }
    }

    @Composable
    fun CreateReportButton(onClick: () -> Unit) {
        val infiniteTransition = rememberInfiniteTransition(label = "breathe")

        val scale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.25f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "breatheScale"
        )

        CupertinoIconButton(
//            backgroundColor= CupertinoTheme.colorScheme.accent,
            colors = CupertinoButtonDefaults.filledButtonColors(),
            onClick = onClick,
            modifier = Modifier
                .size(128.dp)
                .scale(scale)
        ) {
            CupertinoIcon(
                contentDescription = "Breathe",
                modifier = Modifier.size(48.dp),
                imageVector = CupertinoIcons.Default.CameraViewfinder,
            )
        }
    }
}

@Preview
@Composable
private fun UserRoleHomeScreenPreview() {
    PreviewContainer {
        UserRoleHomeScreen().Content()
    }
}
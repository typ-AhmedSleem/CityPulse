package com.typ.citypulse.ui.screens.reporting

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import cafe.adriel.voyager.core.screen.Screen
import com.typ.citypulse.R
import com.typ.citypulse.ui.utils.PreviewContainer
import com.typ.citypulse.usingMocks
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class CRCaptureSceneScreen : Screen {

    @Composable
    override fun Content() {
        // * Runtime
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        // Temp file + URI
        var imageUri by remember { mutableStateOf<Uri?>(Uri.parse("")) }
        var imageCaptured by remember { mutableStateOf(false) }

        // Create a launcher that launches native camera
        val captureImageLauncher = rememberLauncherForActivityResult(TakePicture()) { success ->
            imageCaptured = success
        }

        // * UI * //
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CapturedImageViewer(
                imageCaptured = imageCaptured,
                imageUri = imageUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )

            Spacer(modifier = Modifier.height(32.dp))

            CupertinoButton(
                onClick = {
//                    val uri = createImageUri(context)
//                    imageUri = uri
//                    captureImageLauncher.launch(uri)
                    if (!imageCaptured) {
                        imageCaptured = true

                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(56.dp)
            ) {
                CupertinoText("Capture Image")
            }
        }
    }

    @Composable
    fun CapturedImageViewer(
        modifier: Modifier = Modifier,
        imageCaptured: Boolean,
        imageUri: Uri?,
    ) {
        AnimatedContent(
            targetState = imageCaptured,
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(CupertinoTheme.colorScheme.secondarySystemBackground)
        ) { captured ->
            if (!captured) {
                Box(modifier = Modifier.fillMaxWidth()) { }
            } else {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    painter = loadImageFromUri(imageUri ?: Uri.EMPTY),
                    contentDescription = null,
                )
            }
        }
    }

    @Composable
    private fun loadImageFromUri(imageUri: Uri): Painter {
        return if (usingMocks) {
            painterResource(R.drawable.sample_img_1)
        } else throw NotImplementedError()
    }

    private fun createImageUri(context: Context): Uri {
        val file = context.createImageFile()
        return FileProvider.getUriForFile(
            context,
            "com.typ.citypulse" + ".provider", file
        )
        /*val photoFile = File.createTempFile("citypulse_", ".jpg", context.cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            photoFile
        )*/
    }

    @SuppressLint("SimpleDateFormat")
    private fun Context.createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            externalCacheDir      /* directory */
        )
        return image
    }
}

@Preview
@Composable
private fun CRCaptureSceneScreenPreview() {
    PreviewContainer {
        CRCaptureSceneScreen().Content()
    }
}
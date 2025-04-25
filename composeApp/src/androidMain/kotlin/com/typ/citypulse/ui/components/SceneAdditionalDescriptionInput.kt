package com.typ.citypulse.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.typ.citypulse.R
import io.github.alexzhirkevich.cupertino.CupertinoBorderedTextField
import io.github.alexzhirkevich.cupertino.CupertinoBorderedTextFieldDefaults
import io.github.alexzhirkevich.cupertino.CupertinoText

@Composable
fun SceneAdditionalDescriptionInput(
    modifier: Modifier,
    text: MutableState<String>
) {
    CupertinoBorderedTextField(
        value = text.value.take(200),
        maxLines = 3,
        onValueChange = { text.value = it },
        paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        strokeWidth = 2.dp,
        modifier = modifier.fillMaxWidth()
            .animateContentSize(),
        placeholder = {
            CupertinoText(stringResource(R.string.placeholder_additional_text_for_scene))
        }
    )
}
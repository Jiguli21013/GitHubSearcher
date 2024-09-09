package com.yanchelenko.githubsearcher.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.yanchelenko.githubsearcher.R
import com.yanchelenko.githubsearcher.ui.theme.GitHubSearcherYanchelenkoTheme

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
            .zIndex(1f)
    ) {
        Text(text = errorMessage)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onRetry.invoke()
            },
        ) {
            Text(text = stringResource(id = R.string.try_again_text))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    GitHubSearcherYanchelenkoTheme {
        ErrorScreen(
            errorMessage = "Error 404",
            onRetry = {}
        )
    }
}

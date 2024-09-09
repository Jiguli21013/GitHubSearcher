package com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yanchelenko.githubsearcher.R
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.RepositoryInfoModel
import com.yanchelenko.githubsearcher.ui.theme.GitHubSearcherYanchelenkoTheme

@Composable
fun RepositoryCard(
    repositoryModel: RepositoryInfoModel,
    onRepositoryClicked: (RepositoryInfoModel) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .shadow(3.dp)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .clickable {
                onRepositoryClicked(repositoryModel)
            }
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),

        ) {
            Text(
                text = repositoryModel.repositoryName,
                fontSize = 16.sp,
                fontWeight = FontWeight(weight = 500),
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.forks_count, repositoryModel.forksCount),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight(weight = 800),
                modifier = Modifier.padding(end = 4.dp)
            )
        }
        Text(
            text = repositoryModel.repositoryDescription,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryCardPreview() {
    GitHubSearcherYanchelenkoTheme {
        val repositoryModel = RepositoryInfoModel(
            repositoryName = "Best of the best",
            ownerName = "Owner name",
            forksCount = 24,
            repositoryDescription = "asd asdas asda dasd ad asd asdas ddasd",
            htmlUrl = "url"
        )
        RepositoryCard(
            repositoryModel = repositoryModel,
            onRepositoryClicked = {}
        )
    }
}

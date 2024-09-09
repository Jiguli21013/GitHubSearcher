package com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yanchelenko.githubsearcher.R
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.UserInfoModel
import com.yanchelenko.githubsearcher.ui.theme.GitHubSearcherYanchelenkoTheme

@Composable
fun UserCard(
    userInfoModel: UserInfoModel,
    onUserClicked: (UserInfoModel) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onUserClicked(userInfoModel)
            }
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .shadow(3.dp)
            .fillMaxWidth()
    ) {
        ImageWithPlaceholder(
            imageUrl = userInfoModel.avatarUrl
        )
        Text(
            text = userInfoModel.userLogin,
            fontSize = 16.sp,
            fontWeight = FontWeight(weight = 400),
            modifier = Modifier
        )
        Text(
            text = userInfoModel.userScore,
            fontSize = 18.sp,
            fontWeight = FontWeight(weight = 600),
            color = colorResource(id = R.color.orange),
            modifier = Modifier.padding(end = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    GitHubSearcherYanchelenkoTheme {
        val userInfoModel = UserInfoModel(
            avatarUrl = "",
            userLogin = "Jiguli2101",
            userScore = "450 300",
            htmlUrl = ""
        )
        UserCard(
            userInfoModel = userInfoModel,
            onUserClicked = {}
        )
    }
}

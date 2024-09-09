package com.yanchelenko.githubsearcher.ui.presentation.catalogscreen.views

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.yanchelenko.githubsearcher.R
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel.FolderRepositoryContentItem

@Composable
fun FolderRepositoryItem(
    model: FolderRepositoryContentItem,
    onFolderClicked: (FolderRepositoryContentItem) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onFolderClicked.invoke(model)
            },
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_folder),
            contentDescription = stringResource(id = R.string.folder_icon_desc),
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = model.name ?: "no name")
    }
}

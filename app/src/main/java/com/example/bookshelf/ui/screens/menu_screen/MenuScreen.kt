package com.example.bookshelf.ui.screens.menu_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookshelf.AppDestinations
import com.example.bookshelf.R
import com.example.bookshelf.ui.theme.BookshelfTheme
import com.example.bookshelf.ui.theme.BookshelfThemeSettings

@Composable
fun MenuScreen(
    onSearchClick: () -> Unit,
    onFavClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onSearchClick
        ) {
            Text(text = stringResource(R.string.btn_search))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onFavClick
        ) {
            Text(text = stringResource(R.string.btn_favorite))
        }
        LightDarkThemeItem()
    }
}

@Composable
private fun LightDarkThemeItem(){
    Row (
        Modifier
            .padding(8.dp)
    ){
        Text(
            text = "Turn on the Dark Theme",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = BookshelfThemeSettings.isDarkThemeEnabled,
            onCheckedChange = { BookshelfThemeSettings.isDarkThemeEnabled = it},
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(
        onSearchClick = {
            AppDestinations.QueryScreen.name
        },
        onFavClick ={AppDestinations.FavoriteScreen.name}
    )
}

@Preview
@Composable
fun LightDarkThemeItemPreview(){
    BookshelfTheme {
        LightDarkThemeItem()
    }
}

package com.example.bookshelf.ui.screens.favorite_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.bookshelf.R
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.BookshelfApp
import com.example.bookshelf.ui.screens.components.ErrorScreen
import com.example.bookshelf.ui.screens.components.LoadingScreen
import com.example.bookshelf.ui.screens.query_screen.*
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun FavoritesScreen(
    viewModel: QueryViewModel,
    bookshelfUiState: QueryUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        if (!viewModel.favoriteBooks.isEmpty()) {
            when (bookshelfUiState) {
                is QueryUiState.Loading -> LoadingScreen(modifier)
                is QueryUiState.Success -> GridList(
                    bookshelfList = bookshelfUiState.bookshelfList,
                    viewModel = viewModel,
                    modifier = modifier,
                    onDetailsClick = { }
                )
                else -> ErrorScreen(retryAction, modifier)
            }
        } else {
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.NoFavoriteBooksText))
            }
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    // Создаем фиктивные данные для предварительного просмотра
    val mockBookshelfRepository = object : BookshelfRepository {
        override suspend fun getBooks(query: String): List<Book>? {
            // Возвращает пустой список книг
            return listOf()
        }

        override suspend fun getBook(id: String): Book? {
            // Возвращает null, так как это фиктивная реализация
            return null
        }
    }
    val mockViewModel = QueryViewModel(mockBookshelfRepository)
    val mockBookshelfUiState = QueryUiState.Success(listOf())
    val mockRetryAction = {}

    // Используем функцию FavoritesScreen с фиктивными данными
    FavoritesScreen(
        viewModel = mockViewModel,
        bookshelfUiState = mockBookshelfUiState,
        retryAction = mockRetryAction
    )
}

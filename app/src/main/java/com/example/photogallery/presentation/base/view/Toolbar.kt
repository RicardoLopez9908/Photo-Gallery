package com.example.photogallery.presentation.base.view

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.photogallery.R
import com.example.photogallery.ui.theme.LocalIsDarkMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultToolbar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title: String? = null,
    onTapBack: (() -> Unit)? = null,
    menuContent: (@Composable ColumnScope.() -> Unit)? = null
) {
    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            onTapBack?.let { BackButton(it) }
        },
        title = { title?.let { Text(text = it) } },
        actions = {
            menuContent?.let { content ->
                MenuButton(content)
            }
        }
    )
}

@Composable
private fun MenuButton(
    content: @Composable ColumnScope.() -> Unit
) {
    var showMenuContent by remember { mutableStateOf(false) }
    IconButton(onClick = { showMenuContent = !showMenuContent }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = stringResource(id = R.string.menu_button),
            tint = MaterialTheme.colorScheme.primary
        )
    }
    DropdownMenu(
        expanded = showMenuContent,
        onDismissRequest = { showMenuContent = false },
        content = content
    )
}

@Composable
private fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.back_button),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun RefreshButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = stringResource(id = R.string.retry_button),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ChangeThemeButton(onClick: (Boolean) -> Unit) {
    val isDarkMode = LocalIsDarkMode.current
    IconButton(onClick = { onClick(!isDarkMode) }) {
        Icon(
            painter = painterResource(id = if (isDarkMode) R.drawable.ic_light_mode else R.drawable.ic_dark_mode),
            contentDescription = stringResource(id = R.string.dark_mode_button),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

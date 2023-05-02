package com.example.photogallery.presentation.base.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import com.example.photogallery.R
import com.example.photogallery.util.Response

@Composable
fun BaseScreenStateHandler(
    response: Response<Any?>,
    onTapRetry: () -> Unit,
    successContent: @Composable () -> Unit
) {
    when (response) {
        is Response.Success -> successContent()
        is Response.Loading -> LoadingScreen()
        is Response.Error -> ErrorScreen(
            e = response.exception,
            onTapRetry = onTapRetry
        )
    }
}

/** This screen use LazyVerticalStaggeredGrid with Fixed(2) and DefaultToolbar */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DefaultScrollableScreenWithToolbar(
    title: String? = null,
    changeThemeColor: () -> Unit,
    onTapRetry: () -> Unit,
    onTapBack: (() -> Unit)? = null,
    columns: Int = 2,
    content: LazyStaggeredGridScope.() -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DefaultToolbar(
                scrollBehavior = scrollBehavior,
                title = title,
                onTapBack = onTapBack,
                menuContent = {
                    ChangeThemeButton(changeThemeColor)
                    RefreshButton(onTapRetry)
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = dimensionResource(id = R.dimen.base_horizontal_padding)),
            columns = StaggeredGridCells.Fixed(columns),
            verticalItemSpacing = dimensionResource(id = R.dimen.base_item_spacing_padding),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.base_item_spacing_padding)),
            content = content
        )
    }
}

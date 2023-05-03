package com.example.photogallery.albums.view

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.photogallery.R
import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.presentation.albums.view.AlbumsScreen
import com.example.photogallery.presentation.base.view.TestTagAsyncImageBox
import com.example.photogallery.ui.theme.LocalIsDarkMode
import com.example.photogallery.util.Response
import com.example.photogallery.utils.AlbumListMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var tapRetry = false
    private var colorThemeChanged = false
    private var albumIdSelectedToNavigate: Int? = null

    private val resources = InstrumentationRegistry
        .getInstrumentation().targetContext.resources

    private val albumsTitle = resources.getString(R.string.albums_title)
    private val searchLabel = resources.getString(R.string.search_label)
    private val emptyAlbumListMessage = resources.getString(R.string.empty_albums)
    private val menuButtonContentDescription = resources.getString(R.string.menu_button)
    private val retryButtonContentDescription = resources.getString(R.string.retry_button)
    private val darkModeButtonContentDescription = resources.getString(R.string.dark_mode_button)

    @Before
    fun setUpInitValues() {
        tapRetry = false
        albumIdSelectedToNavigate = null
        colorThemeChanged = false
    }

    @Test
    fun check_all_components_exist_on_screen() {
        val albumList = AlbumListMock.getAlbumList()
        composeTestRule.setContent {
            CompositionLocalProvider(LocalIsDarkMode provides colorThemeChanged) {
                AlbumsScreen(
                    response = Response.Success(
                        data = albumList
                    ),
                    onTapRetry = {},
                    changeThemeColor = {},
                    navigateToPhotos = {}
                )
            }
        }
        albumList.forEach {
            composeTestRule.onNodeWithText(it.title).assertExists()
        }
        composeTestRule.onNodeWithText(albumsTitle).assertExists()
        composeTestRule.onNodeWithText(searchLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(menuButtonContentDescription).assertExists()
            .performClick()
        composeTestRule.onNodeWithContentDescription(darkModeButtonContentDescription)
            .assertExists()
        composeTestRule.onNodeWithContentDescription(retryButtonContentDescription).assertExists()
        composeTestRule.onAllNodesWithTag(TestTagAsyncImageBox, useUnmergedTree = true).assertCountEquals(albumList.size)
    }

    @Test
    fun check_all_component_functionality() {
        val albumList = AlbumListMock.getAlbumList()
        composeTestRule.setContent {
            CompositionLocalProvider(LocalIsDarkMode provides colorThemeChanged) {
                AlbumsScreen(
                    response = Response.Success(
                        data = albumList
                    ),
                    onTapRetry = { tapRetry = true },
                    changeThemeColor = { colorThemeChanged = true },
                    navigateToPhotos = { albumId ->
                        albumIdSelectedToNavigate = albumId
                    }
                )
            }
        }
        // navigate
        assert(albumIdSelectedToNavigate == null)
        composeTestRule.onNodeWithText(albumList[0].title).assertExists().performClick()
        assert(albumIdSelectedToNavigate == albumList[0].id)

        // search
        val searchInput = "search example"
        composeTestRule.onNodeWithText(searchLabel).assertExists().performTextInput(searchInput)
        composeTestRule.onNodeWithText(searchInput).assertExists()

        // open menu options
        composeTestRule.onNodeWithContentDescription(menuButtonContentDescription).assertExists()
            .performClick()

        // change color theme
        assert(!colorThemeChanged)
        composeTestRule.onNodeWithContentDescription(darkModeButtonContentDescription)
            .assertExists().performClick()
        assert(colorThemeChanged)

        // retry button
        assert(!tapRetry)
        composeTestRule.onNodeWithContentDescription(retryButtonContentDescription).assertExists()
            .performClick()
        assert(tapRetry)
    }

    @Test
    fun check_empty_albums_message() {
        composeTestRule.setContent {
            AlbumsScreen(
                response = Response.Success(
                    data = emptyList<AlbumData>()
                ),
                onTapRetry = {},
                changeThemeColor = {},
                navigateToPhotos = {}
            )
        }
        composeTestRule.onNodeWithText(emptyAlbumListMessage).assertExists()
    }
}

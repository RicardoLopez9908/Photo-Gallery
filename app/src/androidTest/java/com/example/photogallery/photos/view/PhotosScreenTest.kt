package com.example.photogallery.photos.view

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
import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.presentation.base.view.TestTagAsyncImageBox
import com.example.photogallery.presentation.photos.view.PhotosScreen
import com.example.photogallery.ui.theme.LocalIsDarkMode
import com.example.photogallery.util.Response
import com.example.photogallery.utils.PhotoListMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotosScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var tapRetry = false
    private var tapBack = false
    private var colorThemeChanged = false
    private var urlAndTitle: Pair<String, String>? = null

    private val resources = InstrumentationRegistry
        .getInstrumentation().targetContext.resources

    private val photosTitle = resources.getString(R.string.photos_title)
    private val searchLabel = resources.getString(R.string.search_label)
    private val emptyPhotoListMessage = resources.getString(R.string.empty_photos)
    private val menuButtonContentDescription = resources.getString(R.string.menu_button)
    private val retryButtonContentDescription = resources.getString(R.string.retry_button)
    private val darkModeButtonContentDescription = resources.getString(R.string.dark_mode_button)
    private val backButtonContentDescription = resources.getString(R.string.back_button)

    @Before
    fun setUpInitValues() {
        tapRetry = false
        tapBack = false
        urlAndTitle = null
        colorThemeChanged = false
    }

    @Test
    fun check_all_components_exist_on_screen() {
        val photoList = PhotoListMock.getPhotoList()
        composeTestRule.setContent {
            CompositionLocalProvider(LocalIsDarkMode provides colorThemeChanged) {
                PhotosScreen(
                    onTapBack = {},
                    response = Response.Success(data = photoList),
                    onTapRetry = {},
                    changeThemeColor = {},
                    navigateToPhotoDetail = { _, _ -> }
                )
            }
        }
        photoList.forEach {
            composeTestRule.onNodeWithText(it.title).assertExists()
        }
        composeTestRule.onNodeWithText(photosTitle).assertExists()
        composeTestRule.onNodeWithText(searchLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(menuButtonContentDescription).assertExists()
            .performClick()
        composeTestRule.onNodeWithContentDescription(darkModeButtonContentDescription)
            .assertExists()
        composeTestRule.onNodeWithContentDescription(retryButtonContentDescription).assertExists()
        composeTestRule.onAllNodesWithTag(TestTagAsyncImageBox, useUnmergedTree = true).assertCountEquals(photoList.size)
    }

    @Test
    fun check_all_component_functionality() {
        val photoList = PhotoListMock.getPhotoList()
        composeTestRule.setContent {
            CompositionLocalProvider(LocalIsDarkMode provides colorThemeChanged) {
                PhotosScreen(
                    onTapBack = { tapBack = true },
                    response = Response.Success(data = photoList),
                    onTapRetry = { tapRetry = true },
                    changeThemeColor = { colorThemeChanged = true },
                    navigateToPhotoDetail = { url, title ->
                        urlAndTitle = Pair(url, title)
                    }
                )
            }
        }
        // navigate
        assert(urlAndTitle == null)
        composeTestRule.onNodeWithText(photoList[0].title).assertExists().performClick()
        assert((urlAndTitle!!.first == photoList[0].url) && urlAndTitle!!.second == photoList[0].title)

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

        // navigate back
        assert(!tapBack)
        composeTestRule.onNodeWithContentDescription(backButtonContentDescription).assertExists()
            .performClick()
        assert(tapBack)
    }

    @Test
    fun check_empty_albums_message() {
        composeTestRule.setContent {
            PhotosScreen(
                onTapBack = { tapBack = true },
                response = Response.Success(data = emptyList<PhotoData>()),
                onTapRetry = { tapRetry = true },
                changeThemeColor = { colorThemeChanged = true },
                navigateToPhotoDetail = { url, title ->
                    urlAndTitle = Pair(url, title)
                }
            )
        }
        composeTestRule.onNodeWithText(emptyPhotoListMessage).assertExists()
    }
}

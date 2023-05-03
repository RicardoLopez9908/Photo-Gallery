package com.example.photogallery.photo.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.photogallery.R
import com.example.photogallery.presentation.base.view.TestTagAsyncImageBox
import com.example.photogallery.presentation.photo.view.PhotoDetailScreen
import com.example.photogallery.utils.CommonValuesMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var tapBack = false

    private val resources = InstrumentationRegistry
        .getInstrumentation().targetContext.resources

    private val backButtonContentDescription = resources.getString(R.string.back_button)

    @Before
    fun setUpInitValue() {
        tapBack = false
    }

    @Test
    fun check_all_components_of_screen() {
        val title = "title_example"
        composeTestRule.setContent {
            PhotoDetailScreen(
                onTapBack = { tapBack = true },
                title = title,
                photoUrl = CommonValuesMock.HTTPS_EXAMPLE
            )
        }
        composeTestRule.onNodeWithText(title).assertExists()
        composeTestRule.onNodeWithTag(TestTagAsyncImageBox).assertExists()

        // navigate back
        assert(!tapBack)
        composeTestRule.onNodeWithContentDescription(backButtonContentDescription).assertExists()
            .performClick()
        assert(tapBack)
    }
}

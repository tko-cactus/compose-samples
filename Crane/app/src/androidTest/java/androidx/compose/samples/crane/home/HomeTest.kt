/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.home

import androidx.compose.material.Surface
import androidx.compose.samples.crane.di.DispatchersModule
import androidx.compose.samples.crane.ui.CraneTheme
import androidx.ui.test.createAndroidComposeRule
import androidx.ui.test.onNodeWithText
import androidx.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(DispatchersModule::class)
@HiltAndroidTest
class HomeTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CraneTheme {
                Surface {
                    CraneHomeContent(
                        onExploreItemClicked = { },
                        onDateSelectionClicked = { },
                        openDrawer = { }
                    )
                }
            }
        }
    }

    @Test
    fun home_navigatesToAllScreens() {
        composeTestRule.onNodeWithText("Explore Flights by Destination").assertExists()
        composeTestRule.onNodeWithText("SLEEP").performClick()
        composeTestRule.onNodeWithText("Explore Properties by Destination").assertExists()
        composeTestRule.onNodeWithText("EAT").performClick()
        composeTestRule.onNodeWithText("Explore Restaurants by Destination").assertExists()
        composeTestRule.onNodeWithText("FLY").performClick()
        composeTestRule.onNodeWithText("Explore Flights by Destination").assertExists()
    }
}

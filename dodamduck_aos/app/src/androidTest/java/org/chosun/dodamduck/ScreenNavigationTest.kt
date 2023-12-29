package org.chosun.dodamduck

import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.navigation.DoDamDuckNavigationGraph
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingScreenNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Before
    fun setupDodamDuckNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            DoDamDuckNavigationGraph(navController = navController)
        }
    }

    @Test
    fun onboardingScreen_loginButtonClicked_navigatesToLoginScreen() {
        composeTestRule.onNodeWithText("로그인").performClick()
        assert(navController.currentBackStackEntry?.destination?.route == BottomNavItem.Login.screenRoute)
    }

    @Test
    fun onboardingScreen_registerButtonClicked_navigatesToRegisterScreen() {
        composeTestRule.onNodeWithText("회원가입").performClick()
        assert(navController.currentBackStackEntry?.destination?.route == BottomNavItem.Register.screenRoute)
    }

}
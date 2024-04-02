package com.example.scanbite_v1_camera_function

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner :: class)
class NavigationTest {

    @Before
    fun setUp() {
        // Launch the main activity before each test
        ActivityScenario.launch(MainActivity::class.java)

    }
    @Test
    fun testHomeFragmentNavigation() {
        //nav to HomeFragment
        onView(withId(R.id.home)).perform(click())

        //verify!
        onView(withId(R.id.fragment_home_parent)).check(matches(isDisplayed()))
    }

    @Test
    fun testReviewFragmentNavigation() {
        //nav to CameraFragment
        onView(withId(R.id.reviews)).perform(click())

        //verify!
        onView(withId(R.id.fragment_review_parent)).check(matches(isDisplayed()))
    }

}
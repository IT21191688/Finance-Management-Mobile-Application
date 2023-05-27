package com.example.madproject

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario.*

import androidx.test.rule.ActivityTestRule


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.madproject", appContext.packageName)
    }


    @RunWith(AndroidJUnit4::class)
    class SignUpActivityTest {

        @Rule
        @JvmField
        val activityRule = ActivityScenarioRule(Sign_Up::class.java)

        @Test
        fun testSignUpSuccess() {


            onView(withId(R.id.fName)).perform(typeText("John"))
            onView(withId(R.id.email)).perform(typeText("john@example.com"))
            onView(withId(R.id.phoneNumber)).perform(typeText("0234567890"))
            onView(withId(R.id.userName)).perform(typeText("john123"), pressKey(KeyEvent.KEYCODE_ENTER))
            onView(withId(R.id.password)).perform(typeText("pass"))
            onView(withId(R.id.signUpBtn)).perform(click())

            //intended(hasComponent(MainActivity::class.java.name))

        }

        @Test
        fun testSignUpValidation() {

            onView(withId(R.id.fName)).perform(typeText(""))
            onView(withId(R.id.email)).perform(typeText("invalid_email"))
            onView(withId(R.id.phoneNumber)).perform(typeText(""))
            onView(withId(R.id.userName)).perform(typeText(""))
            onView(withId(R.id.password)).perform(typeText(""))
            onView(withId(R.id.signUpBtn)).perform(click())
            onView(withText("Please Enter the First Name")).check(matches(isDisplayed()))
            onView(withText("please Enter Email")).check(matches(isDisplayed()))
            onView(withText("Please Enter the Phone Number")).check(matches(isDisplayed()))
            onView(withText("Please enter the User Name")).check(matches(isDisplayed()))
            onView(withText("Please enter the Password")).check(matches(isDisplayed()))


        }



    }



    @RunWith(AndroidJUnit4::class)
    class LoginActivityTest {

        @Rule
        @JvmField
        var activityRule = ActivityTestRule(MainActivity::class.java)

        @Test
        fun testLoginSuccess() {
            // Enter valid credentials
            onView(withId(R.id.userNameL)).perform(typeText("sadeepa123"), pressKey(KeyEvent.KEYCODE_ENTER))
            onView(withId(R.id.passwordL)).perform(typeText("sadeepa123"))

            // Click on the login button
            onView(withId(R.id.LoginBtnL)).perform(click())

            // Verify that the user is redirected to the homepage
            //intended(hasComponent(HomePage::class.java.name))

            // Verify that a toast message is displayed
           // onView(withText("Login Successful"))
            //    .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            //    .check(matches(isDisplayed()))
        }

        @Test
        fun testLoginFailure() {
            // Enter invalid credentials
            onView(withId(R.id.userNameL)).perform(typeText("invalid"),pressKey(KeyEvent.KEYCODE_ENTER))
            onView(withId(R.id.passwordL)).perform(typeText(""))

            // Click on the login button
            onView(withId(R.id.LoginBtnL)).perform(click())

            // Verify that an error message is displayed
           // onView(withText("Invalid Username or Password"))
            //    .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            //    .check(matches(isDisplayed()))
        }
    }





}
package com.sushant.android.microbloggingplatform.post


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.rule.ActivityTestRule
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.ui.postList.PostListActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class PostListActivityTest  {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(PostListActivity::class.java)
    private var mIdlingResource: IdlingResource? = null


    @Test
    fun testScroll() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_post_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.user_name_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.email_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun displayNewsData() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_post_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}
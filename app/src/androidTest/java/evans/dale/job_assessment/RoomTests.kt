package evans.dale.job_assessment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RoomTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    var recyclerId = R.id.room_list_recycler

    @Test
    fun roomListTest() {
        runBlocking {
            delay(400)

            checkTextAtPosition("Blue Sapphire", 0)
            checkTextAtPosition("Capacity: 24", 0)

            checkTextAtPosition("Dragonstone", 1)
            checkTextAtPosition("Capacity: 30", 1)

            checkTextAtPosition("Meeting Room C3", 2)
            checkTextAtPosition("Capacity: 65", 2)

            checkTextAtPosition("Thunderdome", 3)
            checkTextAtPosition("Capacity: 4", 3)
        }
    }

    @Test
    fun roomDetailsTest() {
        runBlocking {
            delay(400)

            onView(ViewMatchers.withId(R.id.room_list_recycler))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                    )
                )

            recyclerId = R.id.details_recycler

            checkTextAtPosition("Location", 0)
            checkTextAtPosition(
                "Hong Kong\nHong Kong Exchange Square\nTwo International Finance Centre\nFloor 12",
                1
            )
            checkTextAtPosition("Features", 2)
            checkTextAtPosition("Blackout Option", 3)
            checkTextAtPosition("Blinds", 4)
            checkTextAtPosition("Folding Wall", 5)
            checkTextAtPosition("Natural Light", 6)
            checkTextAtPosition("Virtual MR", 7)
            checkTextAtPosition("Services", 8)
            checkTextAtPosition("Catering", 9)
            checkTextAtPosition("Facilities", 10)
            checkTextAtPosition("Audio", 11)
            checkTextAtPosition("Control Panel", 12)
            checkTextAtPosition("Flipchart", 13)
            checkTextAtPosition("TV Set", 14)
            checkTextAtPosition("Equipment", 15)
            checkTextAtPosition("Cisco Monitor", 16)
            checkTextAtPosition("Conference Calling", 17)
            checkTextAtPosition("Printer", 18)
            checkTextAtPosition("Projector", 19)
        }
    }

    private fun checkTextAtPosition(text: String, position: Int) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position)
        )

        onView(RecyclerViewMatcher(recyclerId).atPosition(position))
            .check(matches(hasDescendant(withText(text))))
    }

}
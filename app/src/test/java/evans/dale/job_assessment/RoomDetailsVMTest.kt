package evans.dale.job_assessment

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import evans.dale.job_assessment.room.RoomRepository
import evans.dale.job_assessment.room_details.RoomDetailsVM
import evans.dale.job_assessment.service.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test


class RoomDetailsVMTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val roomDetails = RoomDetail(
            "123",
            "name",
            "image-url",
            99,
            Location(
                    KeyNamePair("region-key", "region-name"),
                    KeyNamePair("site-key", "site-name"),
                    KeyNamePair("building-key", "building-name"),
                    KeyNamePair("floor-key", "floor-name")
            ),
            listOf(
                    Feature("feature1-key", "feature1-name"),
                    Feature("feature2-key", "feature2-name")
            ),
            listOf(
                    Service("service1-key", "service1-name", "service1-iconUrl"),
                    Service("service2-key", "service2-name", "service2-iconUrl")
            ),
            listOf(
                    Facility("fac1-key", "fac1-name", "fac1-icon"),
                    Facility("fac2-key", "fac2-name", "fac2-icon")
            ),
            listOf(
                    Equipment("eq1-key", "eq1-name", "eq1-icon"),
                    Equipment("eq2-key", "eq2-name", "eq2-icon")
            )
    )

    private val roomRepoMock = mock<RoomRepository> {
        on { runBlocking { getRoomDetails("key") } } doReturn roomDetails
    }
    private val applicationMock = mock<Application> {
        on { getString(R.string.details_location) } doReturn "location"
        on { getString(R.string.details_features) } doReturn "features"
        on { getString(R.string.details_services) } doReturn "services"
        on { getString(R.string.details_facilities) } doReturn "facilities"
        on { getString(R.string.details_equipment) } doReturn "equipment"
    }
    private val viewModel = RoomDetailsVM(roomRepoMock, applicationMock)

    @Test
    fun getLocationTest() = runBlocking {
        val roomDetailsItems = viewModel.getRoomDetails("key")

        //location header
        assertThat(roomDetailsItems[0], instanceOf(RoomDetailsVM.DetailModel.Header::class.java))
        val header = roomDetailsItems[0] as RoomDetailsVM.DetailModel.Header
        assertThat(header.title, equalTo("location"))

        //location text
        assertThat(roomDetailsItems[1], instanceOf(RoomDetailsVM.DetailModel.Text::class.java))
        val text = roomDetailsItems[1] as RoomDetailsVM.DetailModel.Text
        assertThat(text.value, equalTo("""
            region-name
            site-name
            building-name
            floor-name
            """.trimIndent())
        )
    }

    @Test
    fun getFeaturesTest() = runBlocking {
        val roomDetailsItems = viewModel.getRoomDetails("key")

        //location header
        assertThat(roomDetailsItems[2], instanceOf(RoomDetailsVM.DetailModel.Header::class.java))
        val header = roomDetailsItems[2] as RoomDetailsVM.DetailModel.Header
        assertThat(header.title, equalTo("features"))

        //location text
        assertThat(roomDetailsItems[3], instanceOf(RoomDetailsVM.DetailModel.Text::class.java))
        val feature1 = roomDetailsItems[3] as RoomDetailsVM.DetailModel.Text
        assertThat(feature1.value, equalTo("feature1-name"))

        assertThat(roomDetailsItems[4], instanceOf(RoomDetailsVM.DetailModel.Text::class.java))
        val feature2 = roomDetailsItems[4] as RoomDetailsVM.DetailModel.Text
        assertThat(feature2.value, equalTo("feature2-name"))
    }

    @Test
    fun getServiceTest() = runBlocking {
        val roomDetailsItems = viewModel.getRoomDetails("key")

        //service header
        assertThat(roomDetailsItems[5], instanceOf(RoomDetailsVM.DetailModel.Header::class.java))
        val header = roomDetailsItems[5] as RoomDetailsVM.DetailModel.Header
        assertThat(header.title, equalTo("services"))

        //services
        assertThat(roomDetailsItems[6], instanceOf(RoomDetailsVM.DetailModel.IconText::class.java))
        val feature1 = roomDetailsItems[6] as RoomDetailsVM.DetailModel.IconText
        assertThat(feature1.name, equalTo("service1-name"))
        assertThat(feature1.iconUrl, equalTo("service1-iconUrl"))

        assertThat(roomDetailsItems[7], instanceOf(RoomDetailsVM.DetailModel.IconText::class.java))
        val feature2 = roomDetailsItems[7] as RoomDetailsVM.DetailModel.IconText
        assertThat(feature2.name, equalTo("service2-name"))
        assertThat(feature2.iconUrl, equalTo("service2-iconUrl"))
    }

    @Test
    fun getFacilitiesTest() = runBlocking {
        val roomDetailsItems = viewModel.getRoomDetails("key")

        //facilities header
        assertThat(roomDetailsItems[8], instanceOf(RoomDetailsVM.DetailModel.Header::class.java))
        val header = roomDetailsItems[8] as RoomDetailsVM.DetailModel.Header
        assertThat(header.title, equalTo("facilities"))

        //facilities text
        assertThat(roomDetailsItems[9], instanceOf(RoomDetailsVM.DetailModel.IconText::class.java))
        val feature1 = roomDetailsItems[9] as RoomDetailsVM.DetailModel.IconText
        assertThat(feature1.name, equalTo("fac1-name"))
        assertThat(feature1.iconUrl, equalTo("fac1-icon"))

        assertThat(roomDetailsItems[10], instanceOf(RoomDetailsVM.DetailModel.IconText::class.java))
        val feature2 = roomDetailsItems[10] as RoomDetailsVM.DetailModel.IconText
        assertThat(feature2.name, equalTo("fac2-name"))
        assertThat(feature2.iconUrl, equalTo("fac2-icon"))
    }

    @Test
    fun getEquipmentTest() = runBlocking {
        val roomDetailsItems = viewModel.getRoomDetails("key")

        //equipment header
        assertThat(roomDetailsItems[11], instanceOf(RoomDetailsVM.DetailModel.Header::class.java))
        val header = roomDetailsItems[11] as RoomDetailsVM.DetailModel.Header
        assertThat(header.title, equalTo("equipment"))

        //equipment text
        assertThat(roomDetailsItems[12], instanceOf(RoomDetailsVM.DetailModel.IconText::class.java))
        val feature1 = roomDetailsItems[12] as RoomDetailsVM.DetailModel.IconText
        assertThat(feature1.name, equalTo("eq1-name"))
        assertThat(feature1.iconUrl, equalTo("eq1-icon"))

        assertThat(roomDetailsItems[13], instanceOf(RoomDetailsVM.DetailModel.IconText::class.java))
        val feature2 = roomDetailsItems[13] as RoomDetailsVM.DetailModel.IconText
        assertThat(feature2.name, equalTo("eq2-name"))
        assertThat(feature2.iconUrl, equalTo("eq2-icon"))
    }
}
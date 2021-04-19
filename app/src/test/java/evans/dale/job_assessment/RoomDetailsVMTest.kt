package evans.dale.job_assessment

import android.app.Application
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import evans.dale.job_assessment.room.RoomRepository
import evans.dale.job_assessment.room_details.RoomDetailsVM
import evans.dale.job_assessment.service.KeyNamePair
import evans.dale.job_assessment.service.Location
import evans.dale.job_assessment.service.RoomDetail

class RoomDetailsVMTest {

    val roomDetails = RoomDetail(
            123,
            "name",
            "image-url",
            99,
            Location(
                    KeyNamePair("region-key", "region-name"),
                    KeyNamePair("sitekey", "site-name"),
                    KeyNamePair("building-key", "building-name"),
                    KeyNamePair("floor-key", "floor-name"))
    )

    private val roomRepoMock = mock<RoomRepository> {
        on { getRoomDetails("key") } doReturn roomDetails
    }
    private val applicationMock = mock<Application> {  }
    val viewModel = RoomDetailsVM(roomRepoMock, applicationMock)

    fun getRoomDetailsTests() {

    }
}
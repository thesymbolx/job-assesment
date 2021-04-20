package evans.dale.job_assessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import evans.dale.job_assessment.room.RoomListVM
import evans.dale.job_assessment.room.RoomRepository
import evans.dale.job_assessment.service.Room
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test

class RoomListVMTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val rooms = listOf(
        Room(
            "1111",
            "name1",
            "url1",
            11
        ),
        Room(
            "2222",
            "name2",
            "url2",
            22
        )
    )

    private val roomRepoMock = mock<RoomRepository> {
        on { runBlocking { getRoomList() } } doReturn rooms
    }

    val roomListVM = RoomListVM(roomRepoMock)

    @Test
    fun getRoomTest() = runBlocking {
        val rooms = roomListVM.getRoomList()

        MatcherAssert.assertThat(rooms[0].key, CoreMatchers.equalTo("1111"))
        MatcherAssert.assertThat(rooms[0].title, CoreMatchers.equalTo("name1"))
        MatcherAssert.assertThat(rooms[0].thumbnailUrl, CoreMatchers.equalTo("url1"))
        MatcherAssert.assertThat(rooms[0].capacity, CoreMatchers.equalTo(11))

        MatcherAssert.assertThat(rooms[1].key, CoreMatchers.equalTo("2222"))
        MatcherAssert.assertThat(rooms[1].title, CoreMatchers.equalTo("name2"))
        MatcherAssert.assertThat(rooms[1].thumbnailUrl, CoreMatchers.equalTo("url2"))
        MatcherAssert.assertThat(rooms[1].capacity, CoreMatchers.equalTo(22))
    }
}
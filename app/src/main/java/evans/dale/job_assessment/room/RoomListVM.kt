package evans.dale.job_assessment.room

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomListVM @Inject constructor(
    private val roomRepository: RoomRepository,
) : ViewModel() {

   suspend fun getRoomList(): List<RoomItemVM> {
        val rooms = roomRepository.getRoomList()

        return rooms.map {
            RoomItemVM(
                    it.thumbnailUrl,
                    it.name,
                    it.capacity,
                    it.key
            )
        }
    }
}
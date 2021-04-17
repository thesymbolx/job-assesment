package evans.dale.job_assessment.room


import evans.dale.job_assessment.service.Room
import evans.dale.job_assessment.service.RoomDetail
import evans.dale.job_assessment.service.RoomRepo
import javax.inject.Inject


interface RoomModel {
    suspend fun getRoomList() : List<Room>
    suspend fun getRoomDetails(key: String) : RoomDetail
}

class RoomModelImpl @Inject constructor(
    private val roomService: RoomRepo
) : RoomModel {
    override suspend fun getRoomList(): List<Room> =
        roomService.getRooms()

    override suspend fun getRoomDetails(roomKey: String): RoomDetail =
        roomService.getRoomDetails(roomKey)
}
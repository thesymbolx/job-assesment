package evans.dale.job_assessment.room


import evans.dale.job_assessment.service.Room
import evans.dale.job_assessment.service.RoomDetail
import evans.dale.job_assessment.service.RoomRepo
import javax.inject.Inject


interface RoomRepository {
    suspend fun getRoomList() : List<Room>
    suspend fun getRoomDetails(key: String) : RoomDetail
}

class RoomRepositoryImpl @Inject constructor(
    private val roomService: RoomRepo
) : RoomRepository {
    override suspend fun getRoomList(): List<Room> =
        roomService.getRooms()

    override suspend fun getRoomDetails(roomKey: String): RoomDetail =
        roomService.getRoomDetails(roomKey)
}
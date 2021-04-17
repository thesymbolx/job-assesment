package evans.dale.job_assessment.service

import retrofit2.http.GET
import retrofit2.http.Path

interface RoomRepo {
    @GET("room/rooms.json")
    suspend fun getRooms() : List<Room>

    @GET("room/detail/{key}.json")
    suspend fun getRoomDetails(
        @Path("key") key: String,
    ) : RoomDetail

}
package evans.dale.job_assessment.room_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import evans.dale.job_assessment.R
import evans.dale.job_assessment.room.RoomRepository
import evans.dale.job_assessment.service.RoomDetail
import javax.inject.Inject

@HiltViewModel
class RoomDetailsVM @Inject constructor(
    private val roomRepository: RoomRepository,
    application: Application
) : AndroidViewModel(application) {
    val room: RoomDetail? = null
    val title = MutableLiveData<String>()
    val location = MutableLiveData<String>()

    suspend fun getRoomDetails(roomKey: String) : List<DetailModel> {
        val room = roomRepository.getRoomDetails(roomKey)

        return listOf(
            DetailModel.Header(
                getApplication<Application>().getString(R.string.details_location)
            ),

            DetailModel.Text(
                room.location.let {
                    """${it.region.name}
                |${it.site.name}
                |${it.building.name}
                |${it.floor.name}
            """.trimMargin()
                }
            )

        )
    }

    sealed class DetailModel(val layoutId: Int) {
        class Header(val title: String) : DetailModel(R.layout.details_header)
        class Feature(val name: String) : DetailModel(R.layout.details_header)
        class Text(val value: String) : DetailModel(R.layout.details_text)
    }
}
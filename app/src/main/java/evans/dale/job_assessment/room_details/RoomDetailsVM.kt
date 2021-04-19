package evans.dale.job_assessment.room_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    suspend fun getRoomDetails(roomKey: String): List<DetailModel> {
        val room = roomRepository.getRoomDetails(roomKey)

        return mutableListOf<DetailModel>().apply {
            addHeader(R.string.details_location)
            addText("""
                |${room.location.region.name}
                |${room.location.site.name}
                |${room.location.building.name}
                |${room.location.floor.name}
            """.trimMargin())

            addHeader(R.string.details_features)
            room.features.forEach {
                addText(it.name)
            }

            addHeader(R.string.details_services)
            room.services.forEach {
                addIconTextItem(it.name, it.iconUrl)
            }

            addHeader(R.string.details_facilities)
            room.facilities.forEach {
                addIconTextItem(it.name, it.iconUrl)
            }
        }
    }

    private fun MutableList<DetailModel>.addHeader(titleId: Int) =
            add(DetailModel.Text(getApplication<Application>().getString(titleId)))

    private fun MutableList<DetailModel>.addText(value: String) =
            add(DetailModel.Text(value))

    private fun MutableList<DetailModel>.addIconTextItem(name: String, iconUrl: String) =
            add(DetailModel.IconText(name, iconUrl))

    sealed class DetailModel(val layoutId: Int) {
        class Header(val title: String) : DetailModel(R.layout.details_header)
        class Text(val value: String) : DetailModel(R.layout.details_text)
        class IconText(val name: String, val iconUrl: String) : DetailModel(R.layout.details_icon_text)
    }
}
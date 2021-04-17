package evans.dale.job_assessment.room

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomListVM @Inject constructor(
    private val roomModel: RoomModel
) : ViewModel(), RoomModel by roomModel
package evans.dale.job_assessment

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeVM @Inject constructor() : ViewModel() {

    fun print(){
        Log.d("boobo", "hello")
    }

}
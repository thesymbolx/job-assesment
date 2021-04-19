package evans.dale.job_assessment.utils

import android.content.res.Resources
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("setImageId")
fun setImageId(view: ImageView, id: Int) {
    try {
        view.setImageResource(id)
    } catch (e: Resources.NotFoundException) {
    }
}

@BindingAdapter("imageUrl")
fun imageUrl(view: ImageView, url: String) {
    Glide
            .with(view)
            .asBitmap()
            .load(url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
}
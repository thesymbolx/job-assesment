package evans.dale.job_assessment.room_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import evans.dale.job_assessment.BR
import evans.dale.job_assessment.databinding.RoomItemBinding

class DetailsAdapter(var items: List<RoomDetailsVM.DetailModel> = emptyList()) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                items[index].layoutId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}
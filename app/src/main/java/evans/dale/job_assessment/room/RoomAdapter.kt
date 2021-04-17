package evans.dale.job_assessment.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evans.dale.job_assessment.databinding.RoomItemBinding
import evans.dale.job_assessment.service.Room

class RoomAdapter(var items: List<Room> = emptyList()) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    class ViewHolder(val binding: RoomItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                RoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.test.text = items[position].name
    }

    override fun getItemCount() = items.size
}
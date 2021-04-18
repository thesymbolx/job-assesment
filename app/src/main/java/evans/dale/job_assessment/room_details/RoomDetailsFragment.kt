package evans.dale.job_assessment.room_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import evans.dale.job_assessment.databinding.FragmentRoomDetailsBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomDetailsFragment : Fragment() {

    private val args: RoomDetailsFragmentArgs by navArgs()

    val viewModel: RoomDetailsVM by viewModels()

    lateinit var adapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentRoomDetailsBinding.inflate(inflater).apply {
            adapter = DetailsAdapter()
            detailsRecycler.adapter = this@RoomDetailsFragment.adapter
            detailsRecycler.layoutManager = LinearLayoutManager(context)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Main) {
            val items = viewModel.getRoomDetails(args.roomKey)

            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }

}
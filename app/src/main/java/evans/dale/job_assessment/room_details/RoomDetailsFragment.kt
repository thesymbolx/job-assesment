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
import evans.dale.job_assessment.utils.imageUrl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomDetailsFragment : Fragment() {

    private val args: RoomDetailsFragmentArgs by navArgs()
    val viewModel: RoomDetailsVM by viewModels()
    lateinit var adapter: DetailsAdapter
    lateinit var binding: FragmentRoomDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomDetailsBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@RoomDetailsFragment.viewModel

            adapter = DetailsAdapter()
            detailsRecycler.adapter = this@RoomDetailsFragment.adapter
            detailsRecycler.layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.imageUrl.observe(viewLifecycleOwner) {
            imageUrl(binding.backdrop, it)
        }

        lifecycleScope.launch(Main) {
            val items = viewModel.getRoomDetails(args.roomKey)

            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }

}
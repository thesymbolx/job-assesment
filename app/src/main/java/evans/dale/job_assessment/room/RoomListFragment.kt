package evans.dale.job_assessment.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import evans.dale.job_assessment.databinding.FragmentRoomListBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RoomListFragment : Fragment() {

    private val viewModel: RoomListVM by viewModels()

    lateinit var adapter: RoomAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return FragmentRoomListBinding.inflate(layoutInflater).apply {
            viewModel = this@RoomListFragment.viewModel

            adapter = RoomAdapter {
                findNavController().navigate(
                    RoomListFragmentDirections.actionRoomListFragmentToRoomDetailsFragment(it)
                )
            }
            roomListRecycler.layoutManager = LinearLayoutManager(requireContext())
            roomListRecycler.adapter = adapter
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(IO) {
            val rooms = viewModel.getRoomList()

            withContext(Main) {
                adapter.items = rooms
                adapter.notifyDataSetChanged()
            }
        }
    }
}
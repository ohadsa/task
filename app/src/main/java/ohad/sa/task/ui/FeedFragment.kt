package ohad.sa.task.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ohad.sa.task.databinding.FragmentFeedBinding
import ohad.sa.task.network.NetworkStatusChecker
import ohad.sa.task.ui.adapter.AdapterPosts
import javax.inject.Inject
@AndroidEntryPoint
class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FeedViewModel by viewModels()
    @Inject
    lateinit var myAdapter: AdapterPosts

    @Inject
    lateinit var networkStatusChecker: NetworkStatusChecker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        collectFeedsFlow()
        initSwipeToRefresh()

    }

    private fun initSwipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            if (!networkStatusChecker.hasInternetConnection())
                Snackbar.make(binding.root, "No Internet connection!", Snackbar.LENGTH_SHORT).show()
            collectFeedsFlow()
            binding.swiperefresh.isRefreshing = false;
        }
    }

    private fun initAdapter() {
        myAdapter.likeClickedListener = AdapterPosts.LikeClickedListener { post ->
            viewModel.likeStateChange(post)
        }
        binding.apply {
            feedsRec.apply {
                setHasFixedSize(true)
                val grid = GridLayoutManager(requireContext(), 1)
                grid.orientation = GridLayoutManager.VERTICAL
                layoutManager = grid
                adapter = myAdapter
            }

        }
    }

    private fun collectFeedsFlow() {
        lifecycleScope.launch {
            viewModel.feedsFlow.collectLatest { list ->
                Log.d("LoggerOH", list.toString())
                myAdapter.updateList(list)
                binding.feedsRec.scrollToPosition(0)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
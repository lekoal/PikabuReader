package com.private_projects.pikabu_reader.ui.hot

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.private_projects.pikabu_reader.databinding.FragmentHotBinding
import com.private_projects.pikabu_reader.utils.ViewBindingFragment
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class HotFragment : ViewBindingFragment<FragmentHotBinding>(FragmentHotBinding::inflate) {

    private val scope by lazy {
        getKoin().getOrCreateScope<HotFragment>(SCOPE_ID)
    }

    private val viewModel: HotViewModel by lazy {
        scope.get(named("hot_view_model"))
    }

    private val adapter: HotRVAdapter by lazy {
        scope.get(named("hot_adapter"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.receiveData(1)
        initRV()
        submitPosts()
        checkLoading()
    }

    companion object {
        private const val SCOPE_ID = "hot_fragment_scope_id"
        fun newInstance() = HotFragment()
    }

    private fun initRV() {
        binding.rvHot.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )
        binding.rvHot.adapter = adapter
    }

    private fun submitPosts() {
        viewModel.readDataFromDB()
        viewModel.receivedPosts.observe(viewLifecycleOwner) { posts ->
            adapter.setData(posts)
        }
    }

    private fun checkLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            blockScreen(isLoading)
        }
    }

    private fun blockScreen(isBlock: Boolean) {
        binding.blockScreen.isClickable = isBlock
        if (isBlock) {
            binding.blockScreen.visibility = View.VISIBLE
        } else {
            binding.blockScreen.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        scope.close()
        super.onDestroyView()
    }
}
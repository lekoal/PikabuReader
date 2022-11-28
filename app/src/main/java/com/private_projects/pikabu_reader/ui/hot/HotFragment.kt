package com.private_projects.pikabu_reader.ui.hot

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.private_projects.pikabu_reader.databinding.FragmentHotBinding
import com.private_projects.pikabu_reader.utils.ViewBindingFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class HotFragment : ViewBindingFragment<FragmentHotBinding>(FragmentHotBinding::inflate) {

    private val scope by lazy {
        getKoin().getOrCreateScope<HotFragment>(SCOPE_ID)
    }

    private val viewModel: HotViewModel by lazy {
        scope.get(named("hot_view_model"))
    }

    private val adapter: PagingHotAdapter by lazy {
        scope.get(named("hot_adapter"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        viewModel.receiveData(1)
        submitPosts()
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
        lifecycleScope.launch {
            viewModel.readDataFromDB().observe(viewLifecycleOwner) { pagingData ->
                adapter.submitData(lifecycle, pagingData)
            }
        }
    }

    override fun onDestroyView() {
        scope.close()
        super.onDestroyView()
    }
}
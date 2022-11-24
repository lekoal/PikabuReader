package com.private_projects.pikabu_reader.ui.hot

import android.os.Bundle
import android.view.View
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.receiveData(1)

    }

    companion object {
        private const val SCOPE_ID = "hot_fragment_scope_id"
        fun newInstance() = HotFragment()
    }

    override fun onDestroyView() {
        scope.close()
        super.onDestroyView()
    }
}
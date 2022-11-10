package com.private_projects.pikabu_reader.ui.best

import com.private_projects.pikabu_reader.databinding.FragmentBestBinding
import com.private_projects.pikabu_reader.utils.ViewBindingFragment

class BestFragment : ViewBindingFragment<FragmentBestBinding>(FragmentBestBinding::inflate) {

    companion object {
        fun newInstance() = BestFragment()
    }
}
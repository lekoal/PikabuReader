package com.private_projects.pikabu_reader.ui

import com.private_projects.pikabu_reader.databinding.FragmentFreshBinding
import com.private_projects.pikabu_reader.utils.ViewBindingFragment

class FreshFragment : ViewBindingFragment<FragmentFreshBinding>(FragmentFreshBinding::inflate) {

    companion object {
        fun newInstance() = FreshFragment()
    }
}
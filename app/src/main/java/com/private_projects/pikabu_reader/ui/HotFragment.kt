package com.private_projects.pikabu_reader.ui

import com.private_projects.pikabu_reader.databinding.FragmentHotBinding
import com.private_projects.pikabu_reader.utils.ViewBindingFragment

class HotFragment : ViewBindingFragment<FragmentHotBinding>(FragmentHotBinding::inflate) {



    companion object {
        fun newInstance() = HotFragment()
    }
}
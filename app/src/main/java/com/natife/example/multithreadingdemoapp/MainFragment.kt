package com.natife.example.multithreadingdemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.natife.example.multithreadingdemoapp.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val myViewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel.executeRandomNumberCycle()
        myViewModel.liveData.observe(viewLifecycleOwner, {
            binding.textView.text = it.toString()
        })
    }

}

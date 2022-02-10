package com.natife.example.multithreadingdemoapp

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.natife.example.multithreadingdemoapp.databinding.MainFragmentBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainFragment : Fragment() {

    private val myDispose = CompositeDisposable()
    private lateinit var binding: MainFragmentBinding
    private val myViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

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

//        RxJava
//        myDispose.add(
//            myViewModel
//                .executeRandomNumberCycleByRxJava()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    binding.textView.text = it.toString()
//                }, {
//                    d("RxJavaError", "Something happen here", it)
//                })
//        )

//        Coroutines
        myViewModel.executeRandomNumberCycleByCoroutines()
        myViewModel.liveData.observe(viewLifecycleOwner, {
            binding.textView.text = it.toString()
        })

        //LiveData
//        myViewModel.executeRandomNumberCycleByLiveData()
//        myViewModel.liveData.observe(viewLifecycleOwner, {
//            binding.textView.text = it.toString()
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        myDispose.dispose()
    }

}

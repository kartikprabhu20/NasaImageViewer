package com.challenge.nasaimageviewer.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.challenge.nasaimageviewer.NasaApplication
import com.challenge.nasaimageviewer.R
import com.challenge.nasaimageviewer.adapters.ImageSliderAdapter
import com.challenge.nasaimageviewer.core.Constants
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.databinding.FragmentFullscreenBinding
import com.challenge.nasaimageviewer.util.CustomClickListener
import com.challenge.nasaimageviewer.viewmodels.CustomViewModelFactory
import com.challenge.nasaimageviewer.viewmodels.NasaViewModel
import com.google.firebase.firestore.core.EventManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlinx.coroutines.flow.collect
import kotlin.concurrent.scheduleAtFixedRate

class FullScreenFragment : Fragment(),CustomClickListener{

    private var _binding: FragmentFullscreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: NasaViewModel

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    var delay = 10000

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFullscreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, CustomViewModelFactory((requireActivity().getApplication() as NasaApplication).repository)).get(NasaViewModel::class.java)
//        viewModel.fetchRemoteData()

        val adapter = ImageSliderAdapter(this)
        var imageList:List<NasaImageData>? = null

        viewModel.nasaDataList.observe(getViewLifecycleOwner(), Observer {
            adapter.setImages(it)
            imageList = it
        })
        binding.fullscreenPager.adapter = adapter

        binding.buttonPrevious.setOnClickListener(){
            onPreviousButtonClicked()
        }
        binding.buttonNext.setOnClickListener(){
            onNextButtonClicked()
        }

        binding.buttonDetails.setOnClickListener(){
            val nasaImageData = imageList?.get(binding.fullscreenPager.currentItem)
            if(nasaImageData != null)
                onItemClicked(nasaImageData)
        }

        viewModel.mElapsedTime.observe(getViewLifecycleOwner(), Observer {
            binding.buttonNext.callOnClick()
        })

    }
    fun onPreviousButtonClicked(){
        binding.fullscreenPager.arrowScroll(ViewPager.FOCUS_LEFT)
    }

    fun onNextButtonClicked(){
        binding.fullscreenPager.arrowScroll(ViewPager.FOCUS_RIGHT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onPause() {
//        super.onPause()
//        viewModel.timer.cancel()
//        viewModel.timer.purge()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        viewModel.startTimer()
//    }

    override fun onItemClicked(imageData: NasaImageData) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtra(Constants.FRAGMENT_KEY, imageData)
        }
        startActivity(intent)
    }
}
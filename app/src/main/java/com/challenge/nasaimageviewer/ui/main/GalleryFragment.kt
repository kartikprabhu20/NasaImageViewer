package com.challenge.nasaimageviewer.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.nasaimageviewer.NasaApplication
import com.challenge.nasaimageviewer.R
import com.challenge.nasaimageviewer.adapters.GalleryAdapter
import com.challenge.nasaimageviewer.core.Constants.FRAGMENT_KEY
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.databinding.FragmentGalleryBinding
import com.challenge.nasaimageviewer.util.CustomClickListener
import com.challenge.nasaimageviewer.viewmodels.CustomViewModelFactory
import com.challenge.nasaimageviewer.viewmodels.NasaViewModel

class GalleryFragment : Fragment(), CustomClickListener {

    private var _binding: FragmentGalleryBinding? = null
    lateinit var viewModel: NasaViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            CustomViewModelFactory((requireActivity().getApplication() as NasaApplication).repository)
        ).get(NasaViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GalleryAdapter(this)
        viewModel.nasaDataList.observe(getViewLifecycleOwner(), Observer {
            adapter.setNasaImageData(it)
        })
        binding.galleryRecyclerView.adapter = adapter
//        binding.galleryRecyclerView.layoutManager =  LinearLayoutManager(requireActivity())

        GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false)
            .apply {
                // specify the layout manager for recycler view
                binding.galleryRecyclerView.layoutManager = this
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(imageData: NasaImageData) {
        val frag = DetailFragment()
        val args = Bundle()
        args.putParcelable(FRAGMENT_KEY, imageData)
        frag.arguments = args

        getParentFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, frag)
            .addToBackStack(null)
            .commit()
    }
}
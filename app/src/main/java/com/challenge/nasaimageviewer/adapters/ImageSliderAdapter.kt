package com.challenge.nasaimageviewer.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.databinding.FragmentFullscreenBinding
import com.challenge.nasaimageviewer.databinding.FullscreenItemBinding
import com.challenge.nasaimageviewer.util.CustomClickListener


class ImageSliderAdapter(val mListener: CustomClickListener) : PagerAdapter() {

    var nasaImages = listOf<NasaImageData>()

    fun setImages(nasaImages: List<NasaImageData>){
        this.nasaImages = nasaImages
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = FullscreenItemBinding.inflate( LayoutInflater.from(container.context), container, false)
        val _binding = FragmentFullscreenBinding.inflate( LayoutInflater.from(container.context), container, false)

        val nasaData = nasaImages.get(position)
        binding.nasaimagedata = nasaData
        binding.clickableitem = mListener

        _binding.clickableitem = mListener
        _binding.nasaimagedata = nasaData

        binding.executePendingBindings()
        _binding.executePendingBindings()

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return nasaImages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}
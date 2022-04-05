package com.challenge.nasaimageviewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.nasaimageviewer.adapters.GalleryAdapter.*
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.databinding.GalleryItemBinding
import com.challenge.nasaimageviewer.util.CustomClickListener

class GalleryAdapter internal constructor(
    private val mListener: CustomClickListener)
    : RecyclerView.Adapter<ImageDataHolder>()  {

    var nasaImages = mutableListOf<NasaImageData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDataHolder {
        return ImageDataHolder(
            GalleryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ImageDataHolder, position: Int) {
        val nasaData = nasaImages[position]
        holder.bind(nasaData)
    }

    override fun getItemCount(): Int {
        return nasaImages.size
    }

    fun setNasaImageData(nasaImages: List<NasaImageData>?) {
        if (nasaImages != null) {
            this.nasaImages = nasaImages.toMutableList()
        }
        notifyDataSetChanged()
    }

    inner class ImageDataHolder(val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(nasaData: NasaImageData) {
            binding.nasaimagedata = nasaData
            binding.itemClickable = mListener
            binding.executePendingBindings()
        }
    }
}
package com.challenge.nasaimageviewer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.nasaimageviewer.R
import com.challenge.nasaimageviewer.core.Constants
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.databinding.ActivityDetailBinding

/**
 * Activity to display information related to a single entity
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var imageData: NasaImageData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = intent.extras
        bundle?.apply {
            //Serializable Data
            imageData = intent.getParcelableExtra<NasaImageData>(Constants.FRAGMENT_KEY)!!
        }

//        val extras = intent.extras
//        if (extras != null) {
//            val customer: Customer? = extras.getString("customer") as
//                    Customer?
//            // do something with the customer
//        }

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val frag = DetailFragment()
        val args = Bundle()
        args.putParcelable(Constants.FRAGMENT_KEY, imageData)
        frag.arguments = args

        val ft = getSupportFragmentManager().beginTransaction()
        ft.replace(R.id.detail_activity,frag)
        ft.commit()
    }

}
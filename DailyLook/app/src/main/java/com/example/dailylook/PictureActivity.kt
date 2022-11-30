package com.example.dailylook

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.dailylook.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {

    lateinit var binding:ActivityPictureBinding

    private var photoUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoUri = intent.getParcelableExtra("img")

        binding.ig1.setImageURI(photoUri)


    }
}
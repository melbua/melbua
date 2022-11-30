package com.example.dailylook

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailylook.databinding.ActivityCombinatedLookBinding

class CombinatedLookActivity : AppCompatActivity() {
    lateinit var binding: ActivityCombinatedLookBinding

    private var photoUriTop2: Uri? = null
    private var photoUriBottom: Uri? = null
    private var photoUriShoes: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCombinatedLookBinding.inflate(layoutInflater)
        setContentView(binding.root)


        photoUriTop2 = intent.getParcelableExtra("t1")
        photoUriBottom = intent.getParcelableExtra("b1")
        photoUriShoes = intent.getParcelableExtra("s1")

        binding.topIv.setImageURI(photoUriTop2!!)
        binding.bottomIv.setImageURI(photoUriBottom!!)
        binding.shoesIv.setImageURI(photoUriShoes!!)
    }
}
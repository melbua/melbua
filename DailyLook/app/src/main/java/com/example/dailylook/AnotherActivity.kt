package com.example.dailylook

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailylook.databinding.ActivityAnotherBinding


class AnotherActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnotherBinding
    private var photoUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnotherBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        var receiveData1 = intent.getStringExtra("data1")
//        var receiveData2 = intent.getIntExtra("data2", 0)

        photoUri = intent.getParcelableExtra("uri")
        var receiveName = intent.getStringExtra("name")
        var receiveDest = intent.getStringExtra("dest")

        if (photoUri != null) {

            if (photoUri != null) {

                binding.imageView1.setImageURI(photoUri)
                binding.textView1.setText(receiveName)
                binding.textView22.setText(receiveDest)
            } else {
                binding.imageView1.setImageResource(R.mipmap.ic_launcher_round)
            }
        } else {
            binding.imageView1.setImageResource(R.mipmap.ic_launcher_round)
        }

    }
}
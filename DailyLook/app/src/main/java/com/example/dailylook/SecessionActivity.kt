package com.example.dailylook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylook.databinding.ActivitySecessionBinding

class SecessionActivity : AppCompatActivity(){
    lateinit var binding : ActivitySecessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secessionSecessionBtn.setOnClickListener {
            // id pw가 맞으면 탈퇴처리하고 login 화면으로
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
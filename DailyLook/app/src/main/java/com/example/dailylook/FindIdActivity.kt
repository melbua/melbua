package com.example.dailylook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylook.databinding.ActivityFindIdBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FindIdActivity : AppCompatActivity(){

    lateinit var binding : ActivityFindIdBinding
    lateinit var db : ProfileDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findidFindidBtn.setOnClickListener {
            // 이름과 생년월일이 맞다면 id 팝업 출력 후 다시 login 화면으로
            findId()
            finish()
        }
    }
    private fun findId(){

    }
}

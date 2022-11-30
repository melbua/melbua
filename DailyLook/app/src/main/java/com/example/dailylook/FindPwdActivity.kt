package com.example.dailylook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylook.databinding.ActivityFindPwdBinding

class FindPwdActivity :AppCompatActivity(){

    lateinit var binding : ActivityFindPwdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findpwdFindpwdBtn.setOnClickListener {
            // id가 맞다면 pw 별표시로 표시 팝업 출력 후 다시 login 화면으로
            findPwd()
            finish()
        }
    }
    private fun findPwd(){

    }
}
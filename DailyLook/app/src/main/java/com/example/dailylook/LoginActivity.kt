package com.example.dailylook

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylook.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var db: ProfileDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        db = ProfileDatabase.getInstance(applicationContext)!!

        binding.loginBtn.setOnClickListener {
            //로그인 성공 시 화면 전환 , 로그인 조건 코드 써야됨
            /*   if (checkLogin()) {*/
            Toast.makeText(this, "로그인성공", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MyclosetActivity::class.java)
            startActivity(intent)
            /*  } else {

                  Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()

              }*/
        }
        binding.signupBtn.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.findidBtn.setOnClickListener {
            intent = Intent(this, FindIdActivity::class.java)
            startActivity(intent)
        }

        binding.findpwBtn.setOnClickListener {
            intent = Intent(this, FindPwdActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun checkLogin(): Boolean {
//        var check = false
//        CoroutineScope(Dispatchers.Main).launch {
//            val data = CoroutineScope(Dispatchers.IO).async {
//                db.profileDao().getId()
//            }.await()
//
//            if (data.contains(binding.inputIdEt.text.toString())) {
//                check = true
//            }
//        }
//        return check
//    }
}

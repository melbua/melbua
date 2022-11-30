package com.example.dailylook

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylook.databinding.ActivitySignUpBinding
import kotlinx.coroutines.*

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
//    lateinit var db: ProfileDatabase
    var sex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        db = ProfileDatabase.getInstance(applicationContext)!!

        connectSpinner() //spinner 연결
        connectRadioBtn()   // radiobtnonchecklistener 연결


        binding.signupSignupBtn.setOnClickListener {
            if(checkNullSpace()){
                Toast.makeText(this, "회원가입성공", Toast.LENGTH_SHORT).show()
                addUser()
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun checkNullSpace():Boolean{
        var isExistBlank = false
        var isPWSame = false
        if(binding.signupIdEt.text.toString().isEmpty() || binding.signupPwEt.text.toString().isEmpty() || binding.signupRepwEt.text.toString().isEmpty()){
            isExistBlank = true
        }
        else{
            if(binding.signupRepwEt.text.toString() == binding.signupPwEt.text.toString()){
                isPWSame = true
            }
        }

        if(!isExistBlank && isPWSame){

            // 회원가입 성공 토스트 메세지 띄우기
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

            return true
        }
        else{
            // 상태에 따라 다른 다이얼로그 띄워주기
            if(isExistBlank){   // 작성 안한 항목이 있을 경우
                Toast.makeText(this,"빈칸이 있습니다",Toast.LENGTH_SHORT).show()
            }
            else if(!isPWSame){ // 입력한 비밀번호가 다를 경우
                Toast.makeText(this,"패스워드가 다릅니다",Toast.LENGTH_SHORT).show()
            }
            return false
        }
    }

    private fun connectRadioBtn() {
        binding.signupSexRg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.signup_sex_man_btn -> sex = "man"
                R.id.signup_sex_woman_btn -> sex = "woman"
            }
        }
    }

    private fun connectSpinner() {
        val yearlist = resources.getStringArray(R.array.year_spinner)
        val monthlist = resources.getStringArray(R.array.month_spinner)
        val datelist = resources.getStringArray(R.array.date_spinner)

        val yearadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearlist)
        val monthadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monthlist)
        val dateadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datelist)

        binding.signupYearSpinner.adapter = yearadapter
        binding.signupMonthSpinner.adapter = monthadapter
        binding.signupDateSpinner.adapter = dateadapter
    }

    private fun addUser() {
        var newUser = Profile(
            binding.signupIdEt.text.toString(),
            binding.signupPwEt.text.toString(),
            binding.signupNameEt.text.toString(),
            binding.signupYearSpinner.selectedItem.toString() + binding.signupMonthSpinner.selectedItem.toString() + binding.signupDateSpinner.selectedItem.toString(),
            sex.toString()
        )
        CoroutineScope(Dispatchers.IO).launch {
//            db.profileDao().insert(newUser)
        }
    }
}
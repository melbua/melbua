package com.example.odowan

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.odowan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mButton.setOnClickListener{
           val intent = Intent(this, SeoulActivity::class.java)
            startActivity(intent)
        }
        // 이건 Hash key 찾는 코드임 플젝내용이랑 연관 x
        try {
            val info = packageManager.getPackageInfo("com.example.odowan", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val sign = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                Log.e("hash key TAG", "hash key : $sign")
                //Toast.makeText(getApplicationContext(),sign,     Toast.LENGTH_LONG).show();
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("hash key TAG", "error: $e")
        } catch (e: NoSuchAlgorithmException) {
            Log.e("hash key TAG", "error: $e")
        }

        val bnv_main = findViewById<BottomNavigationView>(R.id.bnv_main)

        // 하단 네비게이션 구현
        bnv_main.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.first -> {
                        HomeFragment()
                    }
                    R.id.second -> {
                        BlankFragment()
                    }
                    R.id.third -> {
                        ListFragment()
                    }
                    else -> {
                        ListFragment();
                    }
                }
            )
            true
        }
        bnv_main.selectedItemId = R.id.first
    }
    // fragment 전환
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}



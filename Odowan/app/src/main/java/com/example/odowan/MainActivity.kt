package com.example.odowan

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.odowan.databinding.ActivityMainBinding
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
        try {     // 해시키
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
    }
}


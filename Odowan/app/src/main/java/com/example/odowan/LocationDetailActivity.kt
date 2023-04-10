package com.example.odowan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.odowan.databinding.ActivityLocationDetailBinding
import net.daum.mf.map.api.MapView


class LocationDetailActivity : AppCompatActivity() {
    lateinit var datas : LocationData
    lateinit var binding : ActivityLocationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //카카오 api
        val mapview = MapView(this)
        binding.mapView.addView(mapview)

        //리사이클러뷰, 전에 구현했던 parcelable로 장소 등등 데이터를 받아오는부분이 여기
        datas = intent.getParcelableExtra<LocationData>("data") as LocationData
        //glide 로 이미지 가져오기
        Glide.with(this).load(datas.img).into(binding.locationDetailImg)
        binding.locationDetailText.text = datas.name

    }
}
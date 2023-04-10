package com.example.odowan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.odowan.databinding.ActivitySeoulBinding

class SeoulActivity : AppCompatActivity() {
    lateinit var  binding: ActivitySeoulBinding
    lateinit var locationAdaptor: LocationAdaptor

    val datas = mutableListOf<LocationData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeoulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        binding.locationinfo.addItemDecoration(VerticalItemDecorator(50))
        binding.locationinfo.addItemDecoration(HorizontalItemDecorator(10))
    }

    private fun initRecycler() {
        locationAdaptor = LocationAdaptor(this)
        binding.locationinfo.adapter = locationAdaptor

        datas.apply {
            add(LocationData(img = R.drawable.namsan, name = "호수공원", location = "양천구"))
            add(LocationData(img = R.drawable.everland, name = "바닥공원", location = "금천구"))
            add(LocationData(img = R.drawable.namsan, name = "하늘공원", location = "종로구"))
            add(LocationData(img = R.drawable.everland, name = "물공원", location = "은평구"))
            add(LocationData(img = R.drawable.namsan, name = "바람공원", location = "중구"))
            add(LocationData(img = R.drawable.everland, name = "태양공원", location = "서구"))

            locationAdaptor.datas = datas
            locationAdaptor.notifyDataSetChanged()

        }
    }
}
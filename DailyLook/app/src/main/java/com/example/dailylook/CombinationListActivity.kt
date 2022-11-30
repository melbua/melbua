package com.example.dailylook

import ViewPagerAdapter
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.dailylook.databinding.ActivityCombinatedLookBinding
import com.example.dailylook.databinding.ActivityCombinationListBinding


class CombinationListActivity : AppCompatActivity() {
    lateinit var binding: ActivityCombinationListBinding

    private var photoUriTop1: Uri? = null
    private var photoUriTop2: Uri? = null
    private var photoUriBottom: Uri? = null
    private var photoUriShoes: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCombinationListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        photoUriTop1 = intent.getParcelableExtra("top1")
        photoUriTop2 = intent.getParcelableExtra("top2")
        photoUriBottom = intent.getParcelableExtra("bottom")
        photoUriShoes = intent.getParcelableExtra("shoes")

       // binding.viewPagerIdol.adapter = ViewPagerAdapter(getIdolList())
        binding.viewPagerIdol.adapter = ViewPagerAdapter(arrayListOf<Uri>(photoUriTop1!!,photoUriTop2!!))
        binding.viewPagerIdol2.adapter = ViewPagerAdapter(arrayListOf<Uri>(photoUriBottom!!))
        binding.viewPagerIdol3.adapter = ViewPagerAdapter(arrayListOf<Uri>(photoUriShoes!!))
        binding.viewPagerIdol.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        binding.combinationPlusBtn.setOnClickListener {
            val intent = Intent(this, CombinatedLookActivity::class.java)
            intent.putExtra("t1",photoUriTop2)
            intent.putExtra("b1",photoUriBottom)
            intent.putExtra("s1",photoUriShoes)
            startActivity(intent)
        }



    }



//
//    private fun getIdolList(): ArrayList<Uri> {
//            return arrayListOf<Uri>(photoUriTop!!, photoUriBottom!!, photoUriShoes!!)
//        }



}

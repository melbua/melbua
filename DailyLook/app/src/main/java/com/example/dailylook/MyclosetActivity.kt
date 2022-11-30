package com.example.dailylook
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylook.databinding.ActivityMyclosetBinding

import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager

private var userList = arrayListOf<DataVo>(
)
private var userListTop = arrayListOf<DataVo>(
)
private var userListBottom = arrayListOf<DataVo>(
)
private var userListShoes = arrayListOf<DataVo>(
)


class MyclosetActivity : AppCompatActivity() {


    lateinit var binding: ActivityMyclosetBinding


    //name = tag, address = dest


    var mAdapter = CustomAdapter(this, userList)


    private var photoUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyclosetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val items = arrayOf("전체조회", "상의조회", "하의조회", "신발조회")

        //어댑터의 아이템은 안드로이드 스튜디오에서 제공해 주는 기본인
//android.R.layout.simple_spinner_dropdown_item 을 사용했습니다.
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)


        binding.spinner.adapter = myAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {
                        mAdapter = CustomAdapter(this@MyclosetActivity, userList)
                        binding.recyclerView.adapter = mAdapter

                    }
                    1 -> {
                        mAdapter = CustomAdapter(this@MyclosetActivity, userListTop)
                        binding.recyclerView.adapter = mAdapter

                    }
                    2 -> {
                        mAdapter = CustomAdapter(this@MyclosetActivity, userListBottom)
                        binding.recyclerView.adapter = mAdapter

                    }
                    3 -> {
                        mAdapter = CustomAdapter(this@MyclosetActivity, userListShoes)
                        binding.recyclerView.adapter = mAdapter

                    }
                    //...
                    else -> {
                        mAdapter = CustomAdapter(this@MyclosetActivity, userList)
                        binding.recyclerView.adapter = mAdapter

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                mAdapter = CustomAdapter(this@MyclosetActivity, userList)
                binding.recyclerView.adapter = mAdapter
            }
        }


//        val layout = LinearLayoutManager(this)
//        recycler_view.layoutManager = layout
//        recycler_view.setHasFixedSize(true)

        // 3개의 열을 갖는 그리드레이아웃 매니저를 설정합니다. 기본값은 세로 방향입니다.
        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        //gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.layoutManager = gridLayoutManager


        binding.ibMenu.setOnClickListener {
            val items = arrayOf("룩 조회", "룩 추가", "로그아웃", "회원탈퇴")

            val builder = AlertDialog.Builder(this)
                .setTitle("Select Item")
                .setItems(items) { dialog, which ->
                    if (which == 0) {
                        val intent = Intent(this, CombinatedLookActivity::class.java)
                        startActivity(intent)
                    }
                    else if(which == 1) {
                        val intent = Intent(this, CombinationListActivity::class.java)
                        startActivity(intent)
                    }
                    else if(which == 2) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else if(which == 3) {
                        val intent = Intent(this, SecessionActivity::class.java)
//                        if(!userListTop.isEmpty()) {
//                            for(i in 0..userListTop.size) {
//                                intent.putExtra("top",userListTop[i].photo)
//                            }
//                        }
//                        if(!userListBottom.isEmpty()) {
//                            for(i in 0..userListBottom.size) {
//                                intent.putExtra("bottom", userListBottom[i].photo)
//                            }
//                        }
//                        if(!userListShoes.isEmpty()) {
//                            for(i in 0..userListShoes.size) {
//                                intent.putExtra("shoes", userListShoes[i].photo)
//                            }
//                        }

                        intent.putExtra("top1",userListTop[0].photo)
                        intent.putExtra("top2",userListTop[1].photo)

                        intent.putExtra("bottom", userListBottom[0].photo)

                        intent.putExtra("shoes", userListShoes[0].photo)


                        startActivity(intent)
                    }
                    //eles if 로 각 which마다 전환 구현
                }
                .show()
        }




        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddclothActivity::class.java)
            startActivity(intent)
        }

        binding.btnRemove.setOnClickListener {
            mAdapter.removeItem(mAdapter.getPosition())
        }

        var tag = intent.getStringExtra("tag")
        var desc = intent.getStringExtra("desc")
        var pay = intent.getIntExtra("pay",0)
        photoUri = intent.getParcelableExtra("uri")


        if (tag != null && desc != null) {
            if(pay == 0){
                mAdapter.addItem(DataVo(tag, "", desc, 0, photoUri),userListTop)
                mAdapter.addItem(DataVo(tag, "", desc, 0, photoUri),userList)
            }
            else if(pay == 1){
                mAdapter.addItem(DataVo(tag, "", desc, 0, photoUri),userListBottom)
                mAdapter.addItem(DataVo(tag, "", desc, 0, photoUri),userList)

            }
            else if(pay == 2){
                mAdapter.addItem(DataVo(tag, "", desc, 0, photoUri),userListShoes)
                mAdapter.addItem(DataVo(tag, "", desc, 0, photoUri),userList)
            }
        }
    }
}









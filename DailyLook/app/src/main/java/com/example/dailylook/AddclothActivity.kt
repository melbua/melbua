package com.example.dailylook

import android.net.Uri
import android.Manifest
import android.R
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.dailylook.databinding.ActivityAddclothBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat

@Suppress("DEPRECATION")
class AddclothActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddclothBinding

    val PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val PERMISSIONS_REQUEST = 100

    private val BUTTON1 = 100
    private val BUTTON2 = 200
    private val BUTTON3 = 300
    private val BUTTON4 = 400
    private val BUTTON5 = 500

    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddclothBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions(PERMISSIONS, PERMISSIONS_REQUEST)




        val items = arrayOf("상의","하의","신발")
        var pay : Int = 0


        val myAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerCategoty.adapter = myAdapter

        binding.spinnerCategoty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        pay = 0
                    }
                    1   ->  {
                        pay = 1

                    }
                    2   ->  {
                        pay = 2
                    }
                    //...
                    else -> {
                        pay = 0
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                pay = 0
            }
        }







        //사진촬영
        binding.cameraBtn.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photoFile = File(
                File("${filesDir}/image").apply{
                    if(!this.exists()){
                        this.mkdirs()
                    }
                },
                newJpgFileName()
            )
            photoUri = FileProvider.getUriForFile(
                this,
                "com.example.dailylook.fileprovider",
                photoFile
            )
            takePictureIntent.resolveActivity(packageManager)?.also{
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, BUTTON3)

            }
        }

        binding.addBtn.setOnClickListener {
            if(photoUri != null) {
                var tag = binding.edittextAddTag.text.toString()
                var desc = binding.edittextAddDescription.text.toString()
                intent = Intent(this, MyclosetActivity::class.java)
                intent.putExtra("tag", tag)
                intent.putExtra("desc", desc)
                intent.putExtra("pay", pay)
                intent.putExtra("uri", photoUri)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "사진이 없습니다", Toast.LENGTH_LONG).show()

            }

        }





    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {

                BUTTON3 -> {
                    val imageBitmap = photoUri?.let { ImageDecoder.createSource(this.contentResolver, it) }
                    binding.imgPreview.setImageBitmap(imageBitmap?.let { ImageDecoder.decodeBitmap(it) })

                    Toast.makeText(this, photoUri?.path, Toast.LENGTH_LONG).show()


                }
            }
        }
    }

    private fun checkPermissions(permissions: Array<String>, permissionsRequest: Int): Boolean {
        val permissionList: MutableList<String> = mutableListOf()
        for (permission in permissions) {
            val result = ContextCompat.checkSelfPermission(this, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission)
            }
        }
        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionList.toTypedArray(),
                PERMISSIONS_REQUEST
            )
            return false
        }
        return true
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한 승인 부탁드립니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }


    private fun newJpgFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }


    private fun saveBitmapAsJPGFile(bitmap: Bitmap) {
        val path = File(filesDir, "image")
        if(!path.exists()){
            path.mkdirs()
        }
        val file = File(path, newJpgFileName())
        var imageFile: OutputStream? = null



        try{
            file.createNewFile()
            imageFile = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageFile)
            imageFile.close()
            Toast.makeText(this, file.absolutePath, Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            null
        }
    }

}


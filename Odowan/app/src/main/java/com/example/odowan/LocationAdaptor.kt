package com.example.odowan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LocationAdaptor(private val context: SeoulActivity) : RecyclerView.Adapter<LocationAdaptor.ViewHolder>() {

    var datas = mutableListOf<LocationData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_view,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.location_name)
        private val txtPlace: TextView = itemView.findViewById(R.id.location_place)
        private val imgLocation: ImageView = itemView.findViewById(R.id.location_img)

        fun bind(item: LocationData) {
            txtName.text = item.name
            txtPlace.text = item.location
            Glide.with(itemView).load(item.img).into(imgLocation)
            //여기부터가 우리 리사이클러뷰에 장소 등등 데이터 값 넘겨주는 부분
            itemView.setOnClickListener {
                Intent(context, LocationDetailActivity::class.java).apply {
                    putExtra("data", item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }
}
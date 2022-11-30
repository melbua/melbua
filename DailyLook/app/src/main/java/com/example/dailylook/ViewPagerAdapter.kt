import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dailylook.R

class ViewPagerAdapter(idolList: ArrayList<Uri>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var item = idolList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.idol.setImageURI(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.idol_list_item, parent, false)){

        val idol = itemView.findViewById<ImageView>(R.id.imageView_idol)!!
        }
}
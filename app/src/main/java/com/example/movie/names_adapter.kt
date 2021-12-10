package com.example.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data_file.collector
import com.example.movie.data_file.movies
import com.squareup.picasso.Picasso


class names_adapter(private val year:String,context: Context):
        RecyclerView.Adapter<names_adapter.names_viewHolder>()
{

    var list:List<movies> = collector().send_obj().toList()

    var filteredWords=list[year.toInt()-2000]

    class names_viewHolder(val view : View):RecyclerView.ViewHolder(view){
        val textView=view.findViewById<TextView>(R.id.item_title)
        val image=view.findViewById<ImageView>(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): names_viewHolder {
        val layout=LayoutInflater.from(parent.context)
                .inflate(R.layout.item_names_view,parent,false)
        return names_viewHolder(layout)
    }

    override fun onBindViewHolder(holder: names_viewHolder, position: Int) {
        var item=filteredWords.name_list[position]
        var img=filteredWords.img_url[position]

        holder.textView.text=item.toString()
        Picasso.get().load(img).into(holder.image)
        holder.image.setOnClickListener {
            val context=holder.view.context
            val intent= Intent(context,Activity_02::class.java)
            intent.putExtra("name",item)
            intent.putExtra("img",img)
            intent.putExtra("year",year)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return filteredWords.name_list.size
    }

}
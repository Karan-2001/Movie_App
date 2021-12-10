package com.example.movie

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView

class year_adapter :RecyclerView.Adapter<year_adapter.year_viewHolder>(),Filterable
{
    var list=(2000).rangeTo(2020).toMutableList().reversed()

    var filterList : MutableList<Int>
    class year_viewHolder(val view: View):RecyclerView.ViewHolder(view)
    {
        val button=view.findViewById<Button>(R.id.button_item)
    }
    init {
        filterList = list.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): year_viewHolder {
        val layout=LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_view,parent,false)

return year_viewHolder(layout)
    }

    override fun onBindViewHolder(holder: year_viewHolder, position: Int) {
        Log.e("_side_publish:","==>"+filterList.toString())
        val item=filterList[position]

        Log.e("In_view_holder:","==>"+filterList.toString())
        holder.button.text=item.toString()
        holder.button.setOnClickListener{
            val context=holder.view.context
            val intent=Intent(context,Activity_01::class.java)
            intent.putExtra("year",holder.button.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return filterList.size
    }


    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filterResults =FilterResults()
                var searchStr:String= p0.toString()
                if(p0==null||p0.isEmpty()){
//                    filterResults.count=filterList.size
//                    filterResults.values=filterList
                        filterList= list as MutableList<Int>
                }else{
                    val item = ArrayList<Int>()
                    for (i in filterList){
                        if(i.toString().contains(searchStr)){
                            item.add(i)
                        }
                    }
//                    filterResults.count=items.size
//                    filterResults.values=items
                        filterList=item.toMutableList()

                }
                Log.e("Inside:","==>"+filterList.toString())
                filterResults.values=filterList
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                //filterList.clear()
                filterList =p1?.values as MutableList<Int>

                notifyDataSetChanged()
                Log.e("_side_publish:","==>"+filterList.toString())

            }

        }
    }
}
package com.example.colourlovers.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.colourlovers.R
import com.example.colourlovers.model.ColourResponseItem
import com.squareup.picasso.Picasso


class MyAdapter(var context: Context,
    var list: List<ColourResponseItem>, private val listner: OnLoverClicked
) : RecyclerView.Adapter<MyAdapter.DataViewHolder>() {
    var ischaked:Boolean = true

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var ivColorPlace: ImageView
        lateinit var ivHeart: ImageView
        lateinit var tvTitle: TextView
        lateinit var tvHex: TextView
        init {
            ivColorPlace = itemView.findViewById(R.id.ivColorPlace) as ImageView
            ivHeart = itemView.findViewById(R.id.ivHeart) as ImageView
            tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
            tvHex = itemView.findViewById(R.id.tvHex) as TextView

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        val viewHolder = DataViewHolder(view)
        view.setOnClickListener{
            if(list[viewHolder.adapterPosition].is_liked==true){
                listner.onItemClicked(list[viewHolder.adapterPosition],false)
            }
            else{
                listner.onItemClicked(list[viewHolder.adapterPosition],true)
            }
        }
        return viewHolder
    }


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: DataViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        var customeUrl=list.get(position).imageUrl
        customeUrl=customeUrl.replace("http://","https://")

        Picasso.get().load(customeUrl).into(holder.ivColorPlace)
        holder.tvTitle.setText(list.get(position).title)
        holder.tvHex.setText(list.get(position).hex)
        if(list.get(position).is_liked==true){
            holder.ivHeart.setBackground(context.getResources().getDrawable(R.drawable.fill_heart));
        }
        else{
            holder.ivHeart.setBackground(context.getResources().getDrawable(R.drawable.blank_heart));
        }
    }

    fun updateList(listt: ArrayList<ColourResponseItem>) {
        this.list = listt;
        notifyDataSetChanged();
    }

}

interface OnLoverClicked{
    fun onItemClicked(item: ColourResponseItem,isliked:Boolean)
}


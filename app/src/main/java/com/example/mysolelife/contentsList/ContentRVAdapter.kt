package com.example.mysolelife.contentsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysolelife.R

class ContentRVAdapter(val context : Context, val items : ArrayList<ContentModel>) : RecyclerView.Adapter<ContentRVAdapter.Viewholder>() {

    // webView구현
    interface ItemClick {
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.Viewholder {
        // 만들어둔 아이템 가져오기
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.Viewholder, position: Int) {

        // 아이템 클릭 시 해당 웹으로 이동
        if(itemClick != null){
            holder.itemView.setOnClickListener{ v->
                itemClick?.onClick(v,position)
            }
        }

        // 아이템의 내용물 하나하나 넣을 수 있게
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        // 전체 아이템의 사이즈
        return items.size
    }

    // content_rv_item.xml에 아이템을 하나하나 넣어줌
    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //item - items중 하나
        fun bindItems(item : ContentModel){
            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)

            contentTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)
        }

    }

    //다 했으면 ContentListActivity에서 연결

}
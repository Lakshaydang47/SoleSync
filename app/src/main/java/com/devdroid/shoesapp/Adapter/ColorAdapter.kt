package com.devdroid.shoesapp.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devdroid.shoesapp.Model.BrandModel
import com.devdroid.shoesapp.R
import com.devdroid.shoesapp.databinding.ViewholderBrandBinding
import com.devdroid.shoesapp.databinding.ViewholderColorBinding

class ColorAdapter(val items:MutableList<String>):RecyclerView.Adapter<ColorAdapter.Viewholder>() {

    private var selectedPosition=-1
    private var lastSelectedPosition=-1
    private lateinit var context: Context

    class Viewholder(val binding:ViewholderColorBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderColorBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position:Int) {


        Glide.with(holder.itemView.context).load(items[position]).into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int = items.size
}
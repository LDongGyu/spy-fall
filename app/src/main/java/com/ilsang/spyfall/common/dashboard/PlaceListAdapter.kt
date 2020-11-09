package com.ilsang.spyfall.common.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilsang.spyfall.R

class PlaceListAdapter(var datas: List<PlaceItem>): RecyclerView.Adapter<PlaceListHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        return PlaceListHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: PlaceListHolder, position: Int) {
        holder.bind(datas[position])
    }

}
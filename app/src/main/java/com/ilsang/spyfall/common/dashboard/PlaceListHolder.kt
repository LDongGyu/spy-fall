package com.ilsang.spyfall.common.dashboard

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilsang.spyfall.R
import de.hdodenhof.circleimageview.CircleImageView

class PlaceListHolder(item: View): RecyclerView.ViewHolder(item){

    val placeIcon: CircleImageView = item.findViewById(R.id.placeImg)
    val placeName: TextView = item.findViewById(R.id.placeTxt)

    fun bind(data: PlaceItem){
        placeIcon.setImageIcon(data.img)
        placeName.text = data.place
    }
}
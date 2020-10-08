package com.bestbus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R
import com.bestbus.models.Tour
import com.bestbus.utils.IOnItemClickListener
import com.bestbus.utils.Util
import kotlin.collections.ArrayList

class TourListAdapter (private val context: Context, private val mTours: ArrayList<Tour>, private val onClick: IOnItemClickListener): RecyclerView.Adapter<TourListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rcv_tour_list, parent, false))
    }

    override fun getItemCount(): Int {
        return mTours.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mTours[position].let {
            holder.tvName.text = it.tourName
            holder.tvPriceOld.text = context.getString(R.string.dollar_format, Util.formatFloat(it.oldPrice))
            holder.tvPrice.text = context.getString(R.string.dollar_format, Util.formatFloat(it.price))
            holder.tvStartTime.text = it.startTime
            holder.tvTime.text = context.getString(R.string.hours, Util.formatFloat(it.time))
            holder.tvEndTime.text = Util.getEndTime(it.startTime, it.time)
            holder.tvFrom.text = it.fromCity
            holder.tvTo.text = it.toCity
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPriceOld: TextView = view.findViewById(R.id.tvPriceOld)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvStartTime: TextView = view.findViewById(R.id.tvStartTime)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val tvEndTime: TextView = view.findViewById(R.id.tvEndTime)
        val tvFrom: TextView = view.findViewById(R.id.tvFrom)
        val tvTo: TextView = view.findViewById(R.id.tvTo)
    }
}
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
import java.text.SimpleDateFormat
import java.util.*
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
            holder.tvPrice1.text = "$" + it.oldPrice
            holder.tvPrice2.text = "$" + it.price
            val c = Calendar.getInstance()
            c.timeInMillis = it.startTime
            holder.tvStartTime.text = SimpleDateFormat("HH:mm", Locale.US).format(c.time)
            holder.tvTime.text = "" + it.time + " hours"
            c.add(Calendar.MINUTE, (it.time * 60).toInt())
            holder.tvEndTime.text = SimpleDateFormat("HH:mm", Locale.US).format(c.time)
            holder.tvFrom.text = it.fromCity
            holder.tvTo.text = it.toCity
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tv_name)
        var tvPrice1: TextView = view.findViewById(R.id.tv_price_1)
        var tvPrice2: TextView = view.findViewById(R.id.tv_price_2)
        var tvStartTime: TextView = view.findViewById(R.id.tv_start_time)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
        var tvEndTime: TextView = view.findViewById(R.id.tv_end_time)
        var tvFrom: TextView = view.findViewById(R.id.tv_from)
        var tvTo: TextView = view.findViewById(R.id.tv_to)
    }
}
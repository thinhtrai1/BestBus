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
            holder.tvPriceOld.text = "$" + it.oldPrice
            holder.tvPrice.text = "$" + it.price
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
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvPriceOld: TextView = view.findViewById(R.id.tvPriceOld)
        var tvPrice: TextView = view.findViewById(R.id.tvPrice)
        var tvStartTime: TextView = view.findViewById(R.id.tvStartTime)
        var tvTime: TextView = view.findViewById(R.id.tvTime)
        var tvEndTime: TextView = view.findViewById(R.id.tvEndTime)
        var tvFrom: TextView = view.findViewById(R.id.tvFrom)
        var tvTo: TextView = view.findViewById(R.id.tvTo)
    }
}
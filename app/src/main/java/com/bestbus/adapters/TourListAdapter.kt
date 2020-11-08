package com.bestbus.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R
import com.bestbus.activities.SelectSeatActivity
import com.bestbus.models.Tour
import com.bestbus.utils.Util
import com.google.gson.Gson
import kotlin.collections.ArrayList

class TourListAdapter (private val context: Context, private val mTours: ArrayList<Tour>, private val date: String?): RecyclerView.Adapter<TourListAdapter.ViewHolder>() {
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
            holder.itemView.setOnClickListener { _ ->
                it.date = date
                context.startActivity(Intent(context, SelectSeatActivity::class.java).putExtra("tour", Gson().toJson(it)))
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val tvName: TextView = view.findViewById(R.id.tvName)
        internal val tvPriceOld: TextView = view.findViewById(R.id.tvPriceOld)
        internal val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        internal val tvStartTime: TextView = view.findViewById(R.id.tvStartTime)
        internal val tvTime: TextView = view.findViewById(R.id.tvTime)
        internal val tvEndTime: TextView = view.findViewById(R.id.tvEndTime)
        internal val tvFrom: TextView = view.findViewById(R.id.tvFrom)
        internal val tvTo: TextView = view.findViewById(R.id.tvTo)
    }
}
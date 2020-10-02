package com.bestbus.adapters

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R
import kotlin.collections.ArrayList

class SeatAdapter (
    private val context: Context,
    private val seatQuantity: Int,
    private val seatBooked: ArrayList<Int>?
): RecyclerView.Adapter<SeatAdapter.ViewHolder>() {

    val selectingList = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rcv_seat, parent, false))
    }

    override fun getItemCount(): Int {
        return seatQuantity
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when {
            selectingList.contains(position) -> {
                holder.itemView.setBackgroundColor(Color.RED)
            }
            seatBooked != null && seatBooked.contains(position) -> {
                holder.itemView.setBackgroundColor(Color.parseColor("#673AB7"))
            }
            else -> {
                holder.itemView.setBackgroundColor(Color.parseColor("#d5d5d5"))
            }
        }
        holder.itemView.setOnClickListener {
            if (seatBooked == null || !seatBooked.contains(position)) {
                if (selectingList.contains(position)) {
                    selectingList.remove(position)
                    holder.itemView.setBackgroundColor(Color.parseColor("#d5d5d5"))
                } else {
                    selectingList.add(position)
                    holder.itemView.setBackgroundColor(Color.RED)
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
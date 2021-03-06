package com.bestbus.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R

class SeatAdapter(
    private val context: Activity,
    private val seatQuantity: Int,
    private val seatBooked: ArrayList<Int>,
    count: Int,
    screenWidth: Int
) : RecyclerView.Adapter<SeatAdapter.ViewHolder>() {

    val selectingList = ArrayList<Int>()
    private val colorAvailable = ContextCompat.getColor(context, R.color.gray)
    private val colorBooked = ContextCompat.getColor(context, R.color.gray_dark)
    private val colorSelecting = ContextCompat.getColor(context, R.color.color_selecting)
    private val itemLayout = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenWidth / (count * 2 + 1))
    private val seatNames = ArrayList<String>()

    init {
        for (i in 0 until seatQuantity) {
            var s = i / (count * 2) + 65
            if (s > 90) {
                s += 6
            }
            seatNames.add(s.toChar().toString().plus(i % (count * 2) + 1))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rcv_seat, parent, false))
    }

    override fun getItemCount(): Int {
        return seatQuantity / 2 + seatQuantity % 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p1 = position * 2
        val p2 = position * 2 + 1

        when {
            selectingList.contains(p1) -> {
                holder.cvSeat1.setCardBackgroundColor(colorSelecting)
            }
            seatBooked.contains(p1) -> {
                holder.cvSeat1.setCardBackgroundColor(colorBooked)
            }
            else -> {
                holder.cvSeat1.setCardBackgroundColor(colorAvailable)
            }
        }

        holder.tvSeat1.text = seatNames[p1]
        if (p2 < seatQuantity) {
            holder.tvSeat2.text = seatNames[p2]
            holder.cvSeat2.visibility = View.VISIBLE
            when {
                selectingList.contains(p2) -> {
                    holder.cvSeat2.setCardBackgroundColor(colorSelecting)
                }
                seatBooked.contains(p2) -> {
                    holder.cvSeat2.setCardBackgroundColor(colorBooked)
                }
                else -> {
                    holder.cvSeat2.setCardBackgroundColor(colorAvailable)
                }
            }
        } else {
            holder.cvSeat2.visibility = View.INVISIBLE
        }

        holder.cvSeat1.setOnClickListener {
            if (!seatBooked.contains(p1)) {
                if (selectingList.contains(p1)) {
                    selectingList.remove(p1)
                    holder.cvSeat1.setCardBackgroundColor(colorAvailable)
                } else {
                    selectingList.add(p1)
                    holder.cvSeat1.setCardBackgroundColor(colorSelecting)
                }
            }
        }

        holder.cvSeat2.setOnClickListener {
            if (!seatBooked.contains(p2)) {
                if (selectingList.contains(p2)) {
                    selectingList.remove(p2)
                    holder.cvSeat2.setCardBackgroundColor(colorAvailable)
                } else {
                    selectingList.add(p2)
                    holder.cvSeat2.setCardBackgroundColor(colorSelecting)
                }
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val tvSeat1: TextView = view.findViewById(R.id.tvSeatName1)
        internal val cvSeat1: CardView = view.findViewById(R.id.cvSeat1)
        internal val tvSeat2: TextView = view.findViewById(R.id.tvSeatName2)
        internal val cvSeat2: CardView = view.findViewById(R.id.cvSeat2)

        init {
            view.layoutParams = itemLayout
        }
    }
}
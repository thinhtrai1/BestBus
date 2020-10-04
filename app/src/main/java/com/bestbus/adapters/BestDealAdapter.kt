package com.bestbus.adapters

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R
import com.bestbus.models.Deal
import com.squareup.picasso.Picasso

class BestDealAdapter(private val context: Activity, private val mDeals: ArrayList<Deal>) : RecyclerView.Adapter<BestDealAdapter.ViewHolder>() {
    private val mItemLayout: ViewGroup.LayoutParams

    init {
        val display = DisplayMetrics()
        context.display?.getRealMetrics(display)
        mItemLayout = ViewGroup.LayoutParams(display.widthPixels * 3 / 4, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rcv_best_deal, parent, false))
    }

    override fun getItemCount(): Int {
        return mDeals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams = mItemLayout
        if (position == 0) {
            holder.viewSpaceStart.visibility = View.VISIBLE
        } else {
            holder.viewSpaceStart.visibility = View.GONE
        }
        mDeals[position].let {
            holder.tvDiscount.text = context.getString(R.string.discount_on, "${it.discount}%")
            holder.tvDescription.text = it.description
            Picasso
                .get()
                .load(it.image)
                .resize(1000, 1000)
                .centerCrop()
                .into(holder.imageView)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDiscount: TextView = view.findViewById(R.id.tvDiscount)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val viewSpaceStart: View = view.findViewById(R.id.viewSpaceStart)
    }
}
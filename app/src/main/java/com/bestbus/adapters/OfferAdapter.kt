package com.bestbus.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bestbus.R
import com.bestbus.models.Offer
import com.bestbus.utils.Util

class OfferAdapter(private val context: Context, private val mOffers: ArrayList<Offer>) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rcv_offer, parent, false))
    }

    override fun getItemCount(): Int {
        return mOffers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val priceOff = Util.formatFloat(mOffers[position].priceOff) + "$ "
        holder.tvOff.text = priceOff
        holder.tvCode.text = mOffers[position].code
        holder.tvCopyCode.setOnClickListener {
            val p = holder.bindingAdapterPosition
            (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
                .setPrimaryClip(ClipData.newPlainText("copy", mOffers[p].code))
            mOffers.removeAt(p)
            notifyItemRemoved(p)
            Toast.makeText(context, context.getString(R.string.offer_code_copied), Toast.LENGTH_SHORT).show()
//            AlertDialog
//                .Builder(context)
//                .setMessage(context.getString(R.string.coming_soon))
//                .setPositiveButton(android.R.string.ok, null)
//                .show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOff: TextView = view.findViewById(R.id.tvOff)
        val tvCode: TextView = view.findViewById(R.id.tvCode)
        val tvCopyCode: TextView = view.findViewById(R.id.tvCopyCode)
    }
}
package com.example.parcial1.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parcial1.R
import com.example.parcial1.RecyclerViewClickListener
import com.example.parcial1.database.entities.Match
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.*

class MatchAdapter(var items: List<Match>, var listener: View.OnClickListener): RecyclerView.Adapter<MatchAdapter.MatchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        var holder = MatchHolder(view)
        holder.onClick(listener)

        return holder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class MatchHolder(itemView: View): RecyclerView.ViewHolder(itemView), RecyclerViewClickListener {

        override fun onClick(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }

        fun bind(item: Match) = with(itemView){
            tv_teamA.text = item.TeamA
            tv_teamB.text = item.TeamB

            if(item.ScoreB > item.ScoreA){
                tv_scoreB.setTextColor(Color.parseColor("#000000"))
                tv_scoreA.setTextColor(Color.parseColor("#000000"))
                if(item.MatchEnd) {
                    tv_result.text = "Ganador: " + item.TeamB
                }
            }else if(item.ScoreB< item.ScoreA){
                tv_scoreA.setTextColor(Color.parseColor("#000000"))
                tv_scoreB.setTextColor(Color.parseColor("#000000"))
                if(item.MatchEnd) {
                    tv_result.text = "Ganador: " + item.TeamA
                }

            }



            val calendar = Calendar.getInstance()
            calendar.time = item.date
            tv_date.text = "DATE: "+calendar.get(Calendar.DAY_OF_MONTH).toString()+"/"+(calendar.get(Calendar.MONTH)+1).toString()

            var minutes = ""
            if(calendar.get(Calendar.MINUTE).toString().length == 1){
                minutes = "0"+calendar.get(Calendar.MINUTE).toString()
            }else{
                minutes = calendar.get(Calendar.MINUTE).toString()
            }
            tv_time.text = "TIME: "+calendar.get(Calendar.HOUR_OF_DAY).toString()+":"+minutes
        }

    }

    fun setData(newList: List<Match>){
        items = newList
        notifyDataSetChanged()
    }

}
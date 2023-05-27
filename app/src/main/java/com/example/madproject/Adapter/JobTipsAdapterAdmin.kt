package com.example.madproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Model.JobTipsModal
import com.example.madproject.R


class JobTipsAdapterAdmin(JobTips:List<JobTipsModal>, internal var context: Context): RecyclerView.Adapter<JobTipsAdapterAdmin.JobTipsViewHolder>() {
    internal var tipslist:List<JobTipsModal> = ArrayList()
    init{

        this.tipslist=JobTips;

    }
    inner class JobTipsViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tipId: TextView =view.findViewById(R.id.tipId)
        var tipTitle: TextView =view.findViewById(R.id.tipTitle)
        var tipDiscription: TextView =view.findViewById(R.id.tipDis)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobTipsViewHolder {

        val view= LayoutInflater.from(context).inflate(R.layout.jobtipsadminread,parent,false)

        return JobTipsViewHolder(view)
    }
    override fun onBindViewHolder(holder: JobTipsViewHolder, position: Int) {

        val tips=tipslist[position]

        holder.tipId.text=tips.tips_id.toString()
        holder.tipTitle.text=tips.jobTipsTitle
        holder.tipDiscription.text=tips.jobTipsDiscription

    }
    override fun getItemCount(): Int {

        println(tipslist.size);
        println("heloooooooooooooooooo")
        return tipslist.size;

    }
}
package com.example.madproject.Adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Model.BankModel
import com.example.madproject.R
import java.io.ByteArrayInputStream

class BankAdapterAdmin(JobsModal:List<BankModel>, internal var context: Context): RecyclerView.Adapter<BankAdapterAdmin.BankViewHolder>() {

    internal var banklist:List<BankModel> = ArrayList()
    init{

        this.banklist=JobsModal;
    }

    inner class BankViewHolder(view: View): RecyclerView.ViewHolder(view){

        var jobId: TextView =view.findViewById(R.id.bankId)
        var jobName: TextView =view.findViewById(R.id.bankName)
        var companyName: TextView =view.findViewById(R.id.amount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {

        val view= LayoutInflater.from(context).inflate(R.layout.readuser,parent,false)

        return BankViewHolder(view)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {

        val jobs=banklist[position]

        holder.jobId.text=jobs.id.toString()
        holder.jobName.text=jobs.bankName
        holder.companyName.text=jobs.bankPrice

    }
    override fun getItemCount(): Int {

        println(banklist.size);
        println("helo")
        return banklist.size;

    }
}
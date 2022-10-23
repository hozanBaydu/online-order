package com.hozanbaydu.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.onlineorderrecyclerrow.view.*

class OnlineOrderFoodsAdapter (val artList:MutableList<OnlineOrdersFoodsModel>): RecyclerView.Adapter<OnlineOrderFoodsAdapter.FoodsHolder>(){



    class FoodsHolder(view: View ) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onlineorderrecyclerrow, parent, false)
        return FoodsHolder(view)


    }

    override fun onBindViewHolder(holder: FoodsHolder, position: Int) {
        holder.itemView.textView.text=artList.get(position).name
         setImageViewResource(holder.itemView.imageView,artList.get(position).image)






        holder.itemView.imageButton3.setOnClickListener {


            val name =artList.get(position).name
            val itemPosition=position.toString()
            Toast.makeText(holder.itemView.context,name+" Sepetten çıkarıldı.", Toast.LENGTH_SHORT).show()




        }

        holder.itemView.imageButton2.setOnClickListener {
            val a =artList.get(position).name
            val itemPosition=position.toString()

            Toast.makeText(holder.itemView.context,a+" Sepete eklendi.", Toast.LENGTH_SHORT).show()



        }

    }

    override fun getItemCount(): Int {
        return artList.size
    }

}
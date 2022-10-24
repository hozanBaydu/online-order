package com.hozanbaydu.lib

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class OnlineOrderGetSqlite(val context: Context,val view: RecyclerView) {


    private var compositeDisposable= CompositeDisposable()
    private lateinit var dp:OnlineOrderFoodsDataBase
    private lateinit var foodDao:OnlineOrderFoodsDao
    fun onlineOrdergetAll(){
        dp= Room.databaseBuilder(context,OnlineOrderFoodsDataBase::class.java,"OnlineOrdersFoodsModel").build()
        foodDao=dp.OnlineOrderFoodsDao()
        compositeDisposable.add(

            foodDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }
    fun handleResponse (list: MutableList<OnlineOrdersFoodsModel>){

        view.layoutManager= LinearLayoutManager(context)
        val foodsAdapter= OnlineOrderFoodsAdapter(list)
        view.adapter=foodsAdapter
    }

}
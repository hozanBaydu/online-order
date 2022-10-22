package com.hozanbaydu.lib

import android.content.Context
import androidx.room.Room
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RxRoom {
    private var compositeDisposable= CompositeDisposable()
    private lateinit var dp:OnlineOrderFoodsDataBase
    private lateinit var foodDao:OnlineOrderFoodsDao

     fun onlineOrderinsert (context: Context, onlineOrdersFoodsModel:OnlineOrdersFoodsModel){

        dp= Room.databaseBuilder(context,OnlineOrderFoodsDataBase::class.java,"OnlineOrdersFoodsModel").build()
        foodDao=dp.OnlineOrderFoodsDao()


        compositeDisposable.add(
            foodDao.insert(onlineOrdersFoodsModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

        )

    }

     fun onlineOrderdeleteAll(context: Context){
        dp= Room.databaseBuilder(context,OnlineOrderFoodsDataBase::class.java,"OnlineOrdersFoodsModel").build()
        foodDao=dp.OnlineOrderFoodsDao()
        compositeDisposable.add(
            foodDao.deleteAllFood()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

        )
    }

     fun onlineOrdergetAll(context: Context){
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

        var result=list




    }
}
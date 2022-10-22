package com.hozanbaydu.lib

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface OnlineOrderFoodsDao {
    @Query("SELECT*FROM OnlineOrdersFoodsModel")
    fun getAll(): Flowable<MutableList<OnlineOrdersFoodsModel>>

    @Query("SELECT*FROM OnlineOrdersFoodsModel WHERE id=:id")
    fun getFood(vararg id:Int): Flowable<MutableList<OnlineOrdersFoodsModel>>

    @Insert
    fun insert (vararg FoodsModel: OnlineOrdersFoodsModel) : Completable

    @Delete
    fun delete(vararg FoodsModel: OnlineOrdersFoodsModel) : Completable

    @Query("DELETE  FROM OnlineOrdersFoodsModel")
    fun deleteAllFood(): Completable
}
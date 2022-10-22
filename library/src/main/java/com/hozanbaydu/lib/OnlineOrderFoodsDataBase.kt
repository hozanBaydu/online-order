package com.hozanbaydu.lib

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OnlineOrdersFoodsModel::class], version = 1)
abstract class OnlineOrderFoodsDataBase : RoomDatabase() {

    abstract fun OnlineOrderFoodsDao(): OnlineOrderFoodsDao
}
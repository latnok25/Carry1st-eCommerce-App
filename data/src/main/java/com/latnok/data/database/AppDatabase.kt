package com.latnok.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latnok.domain.models.CartItem

@Database(entities = [CartItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartItemDAO(): CartItemDAO
}
package com.digispice.mvvmdemo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.digispice.mvvmdemo.data.Cryptocurrency

@Database(entities = arrayOf(Cryptocurrency::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cryptocurrenciesDao(): CryptocurrenciesDao
}
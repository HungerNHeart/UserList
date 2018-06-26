package com.droidmob.zohousers.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.droidmob.zohousers.BuildConfig
import com.droidmob.zohousers.repository.database.dao.UserDao
import com.droidmob.zohousers.repository.dto.common.UserData

@Database(
        entities = arrayOf(UserData::class),
        version = 1,
        exportSchema = false
)
abstract class ZohoDatabase: RoomDatabase() {
    companion object {
        fun create(context: Context): ZohoDatabase {
            val databaseBuilder = if(BuildConfig.IN_MEMORY) {
                Room.inMemoryDatabaseBuilder(context, ZohoDatabase::class.java)
            } else {
                Room.databaseBuilder(context, ZohoDatabase::class.java, "zohoUser.db")
            }
            return databaseBuilder
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun users(): UserDao
}

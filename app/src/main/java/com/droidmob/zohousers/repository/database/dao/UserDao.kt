package com.droidmob.zohousers.repository.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.droidmob.zohousers.repository.dto.common.UserData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserData)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUser(): DataSource.Factory<Int, UserData>

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUsers(): List<UserData>

    @Query("SELECT * FROM user WHERE firstName == :firstName")
    fun getUser(firstName: String): UserData
}
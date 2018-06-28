package com.droidmob.zohousers

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.droidmob.zohousers.repository.UserRepository
import com.droidmob.zohousers.repository.database.ZohoDatabase
import com.droidmob.zohousers.repository.database.dao.UserDao
import com.droidmob.zohousers.repository.dto.common.UserData
import com.droidmob.zohousers.util.Log
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.droidmob.zohousers", appContext.packageName)
    }

    private var mUserDao: UserDao? = null
    private var mDb: ZohoDatabase? = null
    private var context: Context? = null
    lateinit var userRepository: UserRepository

    @Before
    fun createDb() {
        context = InstrumentationRegistry.getTargetContext()
        userRepository = UserRepository()
        mDb = ZohoDatabase.create(context!!)
        mDb!!.clearAllTables()
        userRepository.database = mDb as ZohoDatabase
        mUserDao = mDb!!.users()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDb!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndRead() {
        val user = UserData(1, "Guru", "Karthi", "")
        Log(user.toString())
        mUserDao!!.insert(user)
        val resultData = mUserDao!!.getUser("Guru")
        assert(resultData==user)
    }

    @Test
    @Throws(Exception::class)
    fun writeUserListAndReadLine(){
        val users = mutableListOf<UserData>()
        users.add(UserData(1, "Guru", "Karthi", ""))
        users.add(UserData(2, "Guru", "Karthi", ""))
        users.add(UserData(3, "Guru", "Karthi", ""))
        mUserDao!!.insert(users)
        assertEquals(mUserDao!!.getUsers().size, 3)
    }


}

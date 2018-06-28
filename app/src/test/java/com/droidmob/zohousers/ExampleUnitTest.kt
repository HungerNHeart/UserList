package com.droidmob.zohousers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.support.test.InstrumentationRegistry
import com.droidmob.zohousers.repository.UserRepository
import com.droidmob.zohousers.repository.dto.common.UserData
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var context = InstrumentationRegistry.getTargetContext()
    val userRepository= UserRepository()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun emptyList() {
        val listing = userRepository.getUsers(1)
        val pagedList = getPagedList(listing)
        MatcherAssert.assertThat(pagedList.size, CoreMatchers.`is`(0))
    }

    private fun getPagedList(listing: LiveData<PagedList<UserData>>): PagedList<UserData> {
        val observer = LoggingObserver<PagedList<UserData>>()
        listing.observeForever(observer)
        MatcherAssert.assertThat(observer.value, CoreMatchers.`is`(CoreMatchers.notNullValue()))
        return observer.value!!
    }

    private class LoggingObserver<T> : Observer<T> {
        var value : T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }
}

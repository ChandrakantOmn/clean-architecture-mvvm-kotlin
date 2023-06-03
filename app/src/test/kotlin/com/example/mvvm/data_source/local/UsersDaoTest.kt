package com.example.mvvm.data_source.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UsersDaoTest {
	
	lateinit var usersDao: UsersDao
	lateinit var  database : AppDb
	@Before
	fun setUp(){
		val context = ApplicationProvider.getApplicationContext<Context>()
		 database = AppDb.getDatabase(context)
		usersDao = database.appDao()
	}
	
	/**Should return a single user from the database*/
	@Test
	fun getUserReturnsSingleUser()= runTest {
		val user = User(
			"john_doe",
			1234567890L,
			"https://example.com/avatar.jpg",
			"1234",
			"https://example.com",
			"https://example.com/profile/john_doe",
			"https://example.com/followers/john_doe",
			"https://example.com/following/john_doe",
			"https://example.com/gists/john_doe",
			"https://example.com/starred/john_doe",
			"https://example.com/subscriptions/john_doe",
			"https://example.com/orgs/john_doe",
			"https://example.com/repos/john_doe",
			"https://example.com/events/john_doe",
			"https://example.com/received_events/john_doe",
			"User",
			false
		)
		
		/* whenever(usersDao.getUser()).thenReturn(user)
		val result = usersDao.getUser()
		//verify(usersDao, times(1)).getUsersList()
		assertEquals(user, result) */
		
		usersDao.setUserList(mutableListOf(user))
		val result = usersDao.getUsersList()
		assertEquals(user, result[0])
	}
	
	@After
	fun closeDatabase() {
		database.close()
	}
	
}
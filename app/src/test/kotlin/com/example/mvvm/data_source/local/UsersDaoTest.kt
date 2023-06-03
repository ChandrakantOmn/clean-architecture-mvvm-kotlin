package com.example.mvvm.data_source.local

import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.test.core.app.ApplicationProvider
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner
@RunWith(RobolectricTestRunner::class)
class UsersDaoTest {
	@Mock
	lateinit var usersDao: UsersDao
	@Before
	fun setUp(){
		val context = ApplicationProvider.getApplicationContext<Context>()
		val db = AppDb.getDatabase(context)
		usersDao = db.appDao()
	}
	
	
	/**Should return a single user from the database*/
	@Test
	fun getUserReturnsSingleUser() {
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
		runBlocking {
			`when`(usersDao.getUser()).thenReturn(user)
			val result = usersDao.getUser()
			assertEquals(user, result)
		}
	}
	
}
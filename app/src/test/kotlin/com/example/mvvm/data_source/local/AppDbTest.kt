package com.example.mvvm.data_source.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class AppDbTest {
	
	lateinit var usersDao: UsersDao
	lateinit var  database : AppDb
	@Before
	fun setUp(){
		val context = ApplicationProvider.getApplicationContext<Context>()
		database = AppDb.getDatabase(context)
		usersDao = database.appDao()
	}
	@After
	fun closeDatabase() {
		database.close()
	}
	/**Should return the same instance of AppDb when called multiple times*/
	@Test
	fun getDatabaseReturnsSameInstance() {
		
		assertNotNull(usersDao)
		val user = User(
			"testUser",
			1234L,
			"testAvatarUrl",
			"testGravatarId",
			"testUrl",
			"testHtmlUrl",
			"testFollowersUrl",
			"testFollowingUrl",
			"testGistsUrl",
			"testStarredUrl",
			"testSubscriptionsUrl",
			"testOrganizationsUrl",
			"testReposUrl",
			"testEventsUrl",
			"testReceivedEventsUrl",
			"testType",
			false
		)
		runBlocking {
			usersDao.setUserList(mutableListOf(user))
			val userList = usersDao.getUsersList()
			assertTrue(userList.contains(user))
		}
	}
	
}
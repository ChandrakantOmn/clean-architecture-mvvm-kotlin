package com.example.mvvm.data_source.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AppDbTest {
	
	/**Should return the same instance of AppDb when called multiple times*/
	@Test
	fun getDatabaseReturnsSameInstance() {
		val context = ApplicationProvider.getApplicationContext<Context>()
		
		val db = AppDb.getDatabase(context)
		val dao = db.appDao()
		
		assertNotNull(dao)
		
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
			dao.setUser(user)
			val userList = dao.getUsersList()
			assertTrue(userList.contains(user))
		}
	}
	
}
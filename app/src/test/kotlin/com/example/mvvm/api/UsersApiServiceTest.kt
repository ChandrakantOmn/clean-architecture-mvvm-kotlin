package com.example.mvvm.api

import com.example.mvvm.entities.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class UsersApiServiceTest {
	
	@Mock
	private lateinit var usersApiService: UsersApiService
	
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
	}
	
	/**Should return a list of users with the specified 'since' and 'per_page' parameters*/
	@Test
	 fun getUsersWithSinceAndPerPageParameters() {
		val userList = mutableListOf<User>()
		val user1 = User(
			"user1",
			1L,
			"avatar1",
			"gravatar1",
			"url1",
			"htmlUrl1",
			"followersUrl1",
			"followingUrl1",
			"gistsUrl1",
			"starredUrl1",
			"subscriptionsUrl1",
			"organizationsUrl1",
			"reposUrl1",
			"eventsUrl1",
			"receivedEventsUrl1",
			"type1",
			false
		)
		val user2 = User(
			"user2",
			2L,
			"avatar2",
			"gravatar2",
			"url2",
			"htmlUrl2",
			"followersUrl2",
			"followingUrl2",
			"gistsUrl2",
			"starredUrl2",
			"subscriptionsUrl2",
			"organizationsUrl2",
			"reposUrl2",
			"eventsUrl2",
			"receivedEventsUrl2",
			"type2",
			true
		)
		userList.add(user1)
		userList.add(user2)
		runBlocking {
			whenever(usersApiService.getUsers(1L, 10)).thenReturn(userList)
			val result = usersApiService.getUsers(1L, 10)
			assertEquals(userList, result)
		}
	}
	
	/**Should return an empty list when no users match the specified 'since' and 'per_page' parameters*/
	@Test
	 fun getUsersWithNoMatchingSinceAndPerPageParameters() {
		val userList = mutableListOf<User>()
		val user1 = User(
			"user1",
			1L,
			"avatar1",
			"gravatar1",
			"url1",
			"htmlUrl1",
			"followersUrl1",
			"followingUrl1",
			"gistsUrl1",
			"starredUrl1",
			"subscriptionsUrl1",
			"organizationsUrl1",
			"reposUrl1",
			"eventsUrl1",
			"receivedEventsUrl1",
			"type1",
			false
		)
		val user2 = User(
			"user2",
			2L,
			"avatar2",
			"gravatar2",
			"url2",
			"htmlUrl2",
			"followersUrl2",
			"followingUrl2",
			"gistsUrl2",
			"starredUrl2",
			"subscriptionsUrl2",
			"organizationsUrl2",
			"reposUrl2",
			"eventsUrl2",
			"receivedEventsUrl2",
			"type2",
			true
		)
		userList.add(user1)
		userList.add(user2)
		runBlocking {
			whenever(usersApiService.getUsers(0L, 10)).thenReturn(userList)
			val result = usersApiService.getUsers(0L, 10)
			assertEquals(userList, result)
		}
	}
	
}
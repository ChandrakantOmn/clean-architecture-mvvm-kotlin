package com.example.mvvm.repository

import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class UsersRepositoryTest {
	
	@Mock
	private lateinit var usersRepository: UsersRepository
	
	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
	}
	
	
	/**Should return a list of users when getUsersList is called*/
	@Test
	fun getUsersListReturnsListOfUsers() {
		val userList = listOf(
			User(
				"user1",
				1L,
				"url1",
				"id1",
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
			),
			User(
				"user2",
				2L,
				"url2",
				"id2",
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
		)
		runBlocking {
			whenever(usersRepository.getUsersList()).thenReturn(ResultData.Success(userList))
			val result = usersRepository.getUsersList()
			assertTrue(result is ResultData.Success)
			assertEquals(userList, (result as ResultData.Success).data)
		}
	}
	
}
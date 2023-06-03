package com.example.mvvm.data_source.remote

import com.example.mvvm.MainDispatcherRule
import com.example.mvvm.api.UsersApiService
import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class RemoteDataSourceImplTest {
	@Mock
	private lateinit var api: UsersApiService
	private lateinit var remoteDataSource: RemoteDataSourceImpl
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
	}
	
	/**Should return an error when the API call fails*/
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun getUsersWhenApiCallFails() = runTest{
		val dispatcher = StandardTestDispatcher(testScheduler)
		remoteDataSource = RemoteDataSourceImpl(api, dispatcher)
		whenever(runBlocking { api.getUsers(0, 200) }).thenThrow(RuntimeException("Api call failed"))
		val result = runBlocking { remoteDataSource.getUsers() }
		assertTrue(result is ResultData.Error)
		assertEquals("Api call failed", (result as ResultData.Error).exception.message)
	}
	
	/**Should return a list of users when the API call is successful*/
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun getUsersWhenApiCallIsSuccessful()=runTest {
		
		val userList = mutableListOf<User>()
		userList.add(
			User(
				"johndoe",
				1234567890L,
				"https://example.com/avatar.jpg",
				"1234",
				"https://example.com",
				"https://example.com/profile/johndoe",
				"https://example.com/followers/johndoe",
				"https://example.com/following/johndoe",
				"https://example.com/gists/johndoe",
				"https://example.com/starred/johndoe",
				"https://example.com/subscriptions/johndoe",
				"https://example.com/orgs/johndoe",
				"https://example.com/repos/johndoe",
				"https://example.com/events/johndoe",
				"https://example.com/received_events/johndoe",
				"User",
				false
			)
		)
		val dispatcher = StandardTestDispatcher(testScheduler)
		remoteDataSource = RemoteDataSourceImpl(api, dispatcher)
		whenever(api.getUsers(0, 200)).thenReturn(userList)
		val result = remoteDataSource.getUsers()
		assertTrue(result is ResultData.Success)
		assertEquals(userList, (result as ResultData.Success).data)
	}
	
}
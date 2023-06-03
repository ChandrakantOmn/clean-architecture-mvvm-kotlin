package com.example.mvvm.repository

import com.example.mvvm.data_source.local.UsersDao
import com.example.mvvm.data_source.remote.RemoteDataSource
import com.example.mvvm.di.RemoteDataNotFoundException
import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class UsersRepositoryImplTest {
	
	@Mock
	private lateinit var remoteDataSource: RemoteDataSource
	
	@Mock
	private lateinit var appDao: UsersDao
	
	@Mock
	private lateinit var ioDispatcher: CoroutineDispatcher
	
	
	private lateinit var usersRepositoryImpl: UsersRepositoryImpl
	
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
		
		usersRepositoryImpl = UsersRepositoryImpl(remoteDataSource, appDao, ioDispatcher)
	}
	
	
	/**Should return an error when remote data source returns an error*/
	@Test
	fun getUsersListReturnsErrorWhenRemoteDataSourceReturnsError() {
		val exception = Exception("Error fetching data from remote data source")
		val errorResult = ResultData.Error(exception)
		
		runBlocking {
			whenever(remoteDataSource.getUsers()).thenReturn(errorResult)
			val result = usersRepositoryImpl.getUsersList()
			runBlocking {
				verify(remoteDataSource, times(1)).getUsers()
				verifyNoMoreInteractions(appDao)
				
				assertTrue(result is ResultData.Error)
				//assertEquals(RemoteDataNotFoundException::class.java, result.exception::class.java)
			}
		}
	}
	
	/**Should save the users list to the local database when remote data source returns a successful response*/
	@Test
	fun getUsersListSavesToLocalDatabaseWhenRemoteDataSourceReturnsSuccess() {
		val userList = listOf(
			User("user1", 1L, "", "", "", "", "", "", "", "", "", "", "", "", "", "", false),
			User("user2", 2L, "", "", "", "", "", "", "", "", "", "", "", "", "", "", false)
		)
		
		val resultDataSuccess = ResultData.Success(userList)
		
		runBlocking {
			whenever(remoteDataSource.getUsers()).thenReturn(resultDataSuccess)
			val result = usersRepositoryImpl.getUsersList()
			verify(remoteDataSource, times(1)).getUsers()
			verify(appDao, times(1)).setUserList(userList)
			assertTrue(result is ResultData.Success)
			assertEquals(userList, (result as ResultData.Success).data)
		}
	}
	
	/**Should return a list of users when remote data source returns a successful response*/
	@Test
	fun getUsersListWhenRemoteDataSourceReturnsSuccess() {
		val userList = listOf(
			User(
				"user1", 1L, "url1", "id1", "url1", "htmlUrl1", "followersUrl1", "followingUrl1",
				"gistsUrl1", "starredUrl1", "subscriptionsUrl1", "organizationsUrl1", "reposUrl1",
				"eventsUrl1", "receivedEventsUrl1", "type1", false
			),
			User(
				"user2", 2L, "url2", "id2", "url2", "htmlUrl2", "followersUrl2", "followingUrl2",
				"gistsUrl2", "starredUrl2", "subscriptionsUrl2", "organizationsUrl2", "reposUrl2",
				"eventsUrl2", "receivedEventsUrl2", "type2", true
			)
		)
		
		val resultData = ResultData.Success(userList)
		
		runBlocking {
			whenever(remoteDataSource.getUsers()).thenReturn(resultData)
			whenever(appDao.setUserList(userList)).thenReturn(Unit)
			
			val result = usersRepositoryImpl.getUsersList()
			
			verify(remoteDataSource, times(1)).getUsers()
			verify(appDao, times(1)).setUserList(userList)
			
			assertTrue(result is ResultData.Success)
			assertEquals(userList, (result as ResultData.Success).data)
		}
	}
	
}
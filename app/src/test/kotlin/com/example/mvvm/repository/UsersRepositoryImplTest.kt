package com.example.mvvm.repository

import com.example.mvvm.data_source.local.UsersDao
import com.example.mvvm.data_source.remote.RemoteDataSource
import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import com.example.mvvm.userList
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
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
	
	
	private lateinit var usersRepositoryImpl: UsersRepositoryImpl
	
	@Before
	fun setUp() {
		MockitoAnnotations.openMocks(this)
	}
	
	
	/**Should return an error when remote data source returns an error*/
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun getUsersListReturnsErrorWhenRemoteDataSourceReturnsError() = runTest {
		val exception = Exception("Error fetching data from remote data source")
		val errorResult = ResultData.Error(exception)
		val dispatcher = StandardTestDispatcher(testScheduler)
		usersRepositoryImpl = UsersRepositoryImpl(remoteDataSource, appDao, dispatcher)
		whenever(remoteDataSource.getUsers()).thenReturn(errorResult)
		val result = usersRepositoryImpl.getUsersList()
		verify(remoteDataSource, times(1)).getUsers()
		verifyNoMoreInteractions(appDao)
		
		assertTrue(result is ResultData.Error)
		//assertEquals(RemoteDataNotFoundException::class.java, result.exception::class.java)
		
	}
	
	/**Should save the users list to the local database when remote data source returns a successful response*/
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun getUsersListSavesToLocalDatabaseWhenRemoteDataSourceReturnsSuccess() = runTest {
		val userList = listOf(User("user1", 1L, "", "", "", "", "", "", "", "", "", "", "", "", "", "", false), User("user2", 2L, "", "", "", "", "", "", "", "", "", "", "", "", "", "", false))
		
		val dispatcher = StandardTestDispatcher(testScheduler)
		usersRepositoryImpl = UsersRepositoryImpl(remoteDataSource, appDao, dispatcher)
		
		val resultDataSuccess = ResultData.Success(userList)
		whenever(remoteDataSource.getUsers()).thenReturn(resultDataSuccess)
		val result = usersRepositoryImpl.getUsersList()
		verify(remoteDataSource, times(1)).getUsers()
		verify(appDao, times(1)).setUserList(userList)
		assertTrue(result is ResultData.Success)
		assertEquals(userList, (result as ResultData.Success).data)
		
	}
	
	/**Should return a list of users when remote data source returns a successful response*/
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun getUsersListWhenRemoteDataSourceReturnsSuccess() = runTest {
		
		val dispatcher = StandardTestDispatcher(testScheduler)
		usersRepositoryImpl = UsersRepositoryImpl(remoteDataSource, appDao, dispatcher)
		val resultData = ResultData.Success(userList)
		
		whenever(remoteDataSource.getUsers()).thenReturn(resultData)
		whenever(appDao.setUserList(userList)).thenReturn(Unit)
		
		val result = usersRepositoryImpl.getUsersList()
		
		verify(remoteDataSource, times(1)).getUsers()
		verify(appDao, times(1)).setUserList(userList)
		
		assertTrue(result is ResultData.Success)
		assertEquals(userList, (result as ResultData.Success).data)
		
	}
	
}
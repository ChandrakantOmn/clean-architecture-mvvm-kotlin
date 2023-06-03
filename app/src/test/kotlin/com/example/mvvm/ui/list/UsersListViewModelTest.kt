package com.example.mvvm.ui.list

import com.example.mvvm.entities.ResultData
import com.example.mvvm.entities.User
import com.example.mvvm.repository.UsersRepositoryImpl
import com.example.mvvm.userList
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UsersListViewModelTest {
	
	@Mock
	private val userRepository: UsersRepositoryImpl? = null
	
	private var userListViewModel: UsersListViewModel? = null
	
	@Before
	fun setup() {
		MockitoAnnotations.openMocks(this)
		userListViewModel = userRepository?.let { UsersListViewModel(it) }
	}
	
	@Test
	fun testGetUsers() = runTest{
		// Create a list of mocked users
		
		
		// Mock the repository's getUsers() method to return the mocked users
		Mockito.`when`(userRepository?.getUsersList()).thenReturn(ResultData.Success(userList))
		
		// Call the method in the ViewModel that fetches users
		userListViewModel?.getUserLists()
		
		// Verify that the ViewModel correctly received the users from the repository
		assertEquals(userList, userListViewModel?.resultListStats?.value)
	}
	
	@Test
	fun testErrorState() = runTest {
		// Mock the repository's getUsers() method to throw an exception
		Mockito.`when`(userRepository?.getUsersList()).thenThrow(RuntimeException("Error fetching users"))
		
		// Call the method in the ViewModel that fetches users
		userListViewModel?.getUserLists()
		
		// Verify that the ViewModel correctly handles the error state
		assertTrue(userListViewModel!!.errorMessage.value!!.isNotEmpty())
	}
}

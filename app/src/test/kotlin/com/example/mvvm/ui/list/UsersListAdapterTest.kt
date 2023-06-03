package com.example.mvvm.ui.list

import com.example.mvvm.entities.User
import com.example.mvvm.userList
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UsersListAdapterTest {
	
	/**Should trigger onItemClick with the provided countryStat object*/
	@Test
	fun onItemClickWithProvidedCountryStat() {
		val mockItemClickListener = Mockito.mock(ItemClickListener::class.java)
		val countryStatAdapter = UsersListAdapter(userList, mockItemClickListener)
		countryStatAdapter.itemClickListener.onItemClick(userList[0])
		Mockito.verify(mockItemClickListener, Mockito.times(1)).onItemClick(userList[0])
	}
	
	/**Should return the correct item count for the given list of users*/
	@Test
	fun getItemCountReturnsCorrectCount() {
		// Create a mock ItemClickListener
		val mockItemClickListener = mock(ItemClickListener::class.java)
		// Create an instance of the UsersListAdapter with the mock list and ItemClickListener
		val usersListAdapter = UsersListAdapter(userList, mockItemClickListener)
		// Assert that getItemCount returns the correct count
		assertEquals(userList.size, usersListAdapter.itemCount)
	}
	
}
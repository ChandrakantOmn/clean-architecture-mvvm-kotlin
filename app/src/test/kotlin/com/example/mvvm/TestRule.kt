package com.example.mvvm

import com.example.mvvm.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
	private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
	override fun starting(description: Description) {
		Dispatchers.setMain(testDispatcher)
	}
	
	override fun finished(description: Description) {
		Dispatchers.resetMain()
	}
}


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
	), User(
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

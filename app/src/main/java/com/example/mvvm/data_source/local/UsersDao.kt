package com.example.mvvm.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.mvvm.entities.User

@Dao
interface UsersDao {
	@Query("SELECT * FROM User")
	suspend fun getUsersList(): List<User>
	
	@Insert(onConflict = REPLACE)
	suspend fun setUserList(listCountriesStats: List<User>)
	
}
package com.example.mvvm.data_source.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDb : RoomDatabase() {
	abstract fun appDao(): UsersDao
	companion object {
		@Volatile
		private var INSTANCE: AppDb? = null
		fun getDatabase(context: Context): AppDb {
			return INSTANCE ?: synchronized(this) {
				Room.databaseBuilder(context.applicationContext, AppDb::class.java, "app_db")
					.fallbackToDestructiveMigration()
					.build()
					.also { INSTANCE = it }
			}
		}
	}
}

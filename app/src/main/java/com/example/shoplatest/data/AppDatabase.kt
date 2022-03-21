package com.example.shoplatest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val categoryDatabaseDao: CategoryDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.categoryDatabaseDao)
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .addCallback(AppDatabaseCallback(CoroutineScope(SupervisorJob())))
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

        suspend fun populateDatabase(categoryDao: CategoryDao) {
            categoryDao.deleteAll()

            var category = CategoryEntity("Молоко", "milk")
            categoryDao.insert(category)
            category = CategoryEntity("Мясо", "meat")
            categoryDao.insert(category)
            category = CategoryEntity("Творог", "tvorog")
            categoryDao.insert(category)
            category = CategoryEntity("Овощи", "veget")
            categoryDao.insert(category)
            category = CategoryEntity("Фрукты", "fruit")
            categoryDao.insert(category)
        }
    }

}

package com.lijewski.sample.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TextMessageEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun textMessageDao(): TextMessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        const val DB_NAME = "app_database"
        const val MESSAGES_TABLE = "messages_table"

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

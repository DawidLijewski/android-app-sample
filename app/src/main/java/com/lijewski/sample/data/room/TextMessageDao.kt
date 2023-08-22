package com.lijewski.sample.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TextMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMessage(textMessageEntity: TextMessageEntity)

    @Update
    suspend fun updateMessage(textMessageEntity: TextMessageEntity)

    @Delete
    suspend fun deleteMessage(textMessageEntity: TextMessageEntity)

    @Query("DELETE FROM ${AppDatabase.MESSAGES_TABLE}")
    suspend fun deleteAllMessages()

    @Query("SELECT * FROM ${AppDatabase.MESSAGES_TABLE} ORDER BY id ASC")
    fun readAllMessages(): Flow<List<TextMessageEntity>>
}

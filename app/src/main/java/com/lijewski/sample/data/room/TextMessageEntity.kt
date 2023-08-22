package com.lijewski.sample.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lijewski.sample.data.model.TextMessage
import com.lijewski.sample.data.room.AppDatabase.Companion.MESSAGES_TABLE
import java.time.LocalDateTime

@Entity(tableName = MESSAGES_TABLE)
data class TextMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "dateCreated" ) val dateCreated: String,
)

fun TextMessageEntity.convertToDats(): TextMessage {
    return TextMessage(
        id = this.id,
        name = this.name,
        text = this.text,
        dateCreated = LocalDateTime.parse(this.dateCreated)
    )
}

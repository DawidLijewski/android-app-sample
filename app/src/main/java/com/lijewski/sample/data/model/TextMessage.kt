package com.lijewski.sample.data.model

import com.lijewski.sample.data.room.TextMessageEntity
import java.time.LocalDateTime

data class TextMessage(
    val id: Int,
    val name: String,
    val text: String,
    val dateCreated: LocalDateTime,
)

fun TextMessage.convertToEntity(): TextMessageEntity {
    return TextMessageEntity(
        name = this.name,
        text = this.text,
        dateCreated = this.dateCreated.toString()
    )
}

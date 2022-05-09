package com.felipecoronado.postsapp_zemoga.data.utils

import android.util.Base64.encodeToString
import androidx.room.TypeConverter
import com.felipecoronado.postsapp_zemoga.data.database.room.models.FavoritePosts
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

class DataConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
    @TypeConverter
    fun fromList(value :MutableList<FavoritePosts>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<MutableList<FavoritePosts>>(value)
}

package com.example.news.db

import androidx.room.TypeConverter
import com.example.news.models.Source
import org.json.JSONObject

/**
 * In this class, we convert the source type to String using first method and
 * again convert back to Source type in the second method. This way the database
 * is able to access this Source object.
 */

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return JSONObject().apply {
            put("id", source.id)
            put("name", source.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Source {
        val json = JSONObject(source)
        return Source(json.getString("id"), json.getString("name"))
//        return SchoolList(json.getInt("id"), json["name"] as List<SchoolListItem>)
    }
}
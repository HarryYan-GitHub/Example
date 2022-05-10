package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.codingwithmitch.daggerhiltplayground.firebase.CityRemoteEntity
import com.codingwithmitch.daggerhiltplayground.utils.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromCityJson(json: String): CityRemoteEntity {
        return jsonParser.fromJson<CityRemoteEntity>(
            json,
            object : TypeToken<CityRemoteEntity>(){}.type
        ) ?: CityRemoteEntity()
    }

    @TypeConverter
    fun toCityJson(city: CityRemoteEntity): String {
        return jsonParser.toJson(
            city,
            object : TypeToken<CityRemoteEntity>(){}.type
        ) ?: ""
    }
}
package ohad.sa.task.localdb

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@ProvidedTypeConverter
class ImageListConverter {
    @TypeConverter
    fun fromSource(source: List<String>): String =
        Gson().toJson(source)

    @TypeConverter
    fun toSource(str: String): List<String> {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(str, listType)
    }

}
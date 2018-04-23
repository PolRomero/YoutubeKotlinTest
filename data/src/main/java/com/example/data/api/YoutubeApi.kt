package com.example.data.api
import android.util.Log
import com.example.data.model.HistoryData
import com.example.data.model.VideoData
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

interface YoutubeApi {

    @GET("search")
    fun searchResults(@Query("key") key: String,
                      @Query("part") part: String,
                      @Query("q") query: String,
                      @Query("type") kind: String = "video",
                      @Query("maxResults") num: Int = 50) : Single<HistoryData>
}

class SearchAdapter : TypeAdapter<HistoryData>() {
    override fun write(out: JsonWriter?, value: HistoryData?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(reader: JsonReader?): HistoryData {
        Log.d("Data/YoutubeApi/Reader", "Parsing Json")
        reader?.beginObject()
        val history = HistoryData()
        for (i in 0..9) { reader?.skipValue() }
        if(reader?.nextName() == "items") {
            Log.d("Data/YoutubeApi/Reader", "Finds items")
            reader.beginArray()
            while(reader.hasNext()) {
                val parameters = ArrayList<String>()
                reader.beginObject()
                while(reader.hasNext()) {
                    when (reader.nextName()) {
                        "id" -> {
                            reader.beginObject(); reader.skipValue(); reader.skipValue(); reader.skipValue()
                            parameters.add(reader.nextString()); reader.endObject()
                        }
                        "snippet" -> {
                            reader.beginObject()
                            while (reader.hasNext()) {
                                when (reader.nextName()) {
                                    /*"publishedAt" -> parameters.add(reader.nextString())
                                    "channelId" -> parameters.add(reader.nextString())*/
                                    "title" -> parameters.add(reader.nextString())
                                    "description" -> parameters.add(reader.nextString())
                                    "thumbnails" -> {
                                        reader.beginObject(); reader.skipValue(); reader.beginObject(); reader.skipValue()
                                        parameters.add(reader.nextString()); for( i in 0..3) { reader.skipValue() }
                                        reader.endObject(); for( i in 0..3) { reader.skipValue() }; reader.endObject()
                                    }
                                    "channelTitle" -> parameters.add(reader.nextString())
                                    else -> reader.skipValue()
                                }
                            }
                            reader.endObject()
                        }
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
                val video = VideoData(parameters[0], parameters[1], parameters[2],
                        parameters[3], parameters[4])
                history.add(video)
            }
        }
        return history
    }

}
package com.example.data.model

import io.realm.RealmObject

open class VideoData(var id : String = "",
                     var title : String = "",
                     var description : String = "",
                     var thumbnailUrl : String = "",
                     var channel : String = ""
                ): RealmObject()
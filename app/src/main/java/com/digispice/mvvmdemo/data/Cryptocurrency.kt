package com.digispice.mvvmdemo.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
    tableName = "cryptocurrencies"
)
data class Cryptocurrency(

    @Json(name = "id")
    @PrimaryKey
    val id: String,

    @Json(name = "name")
    val name: String?

    // ... other attributes

) : Serializable
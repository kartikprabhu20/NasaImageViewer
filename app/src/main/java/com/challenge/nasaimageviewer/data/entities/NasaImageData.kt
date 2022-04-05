package com.challenge.nasaimageviewer.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Entity/model object for Nasa information which can be retrieved from the json given by nasa apo
 */
@Parcelize
@Entity(tableName = "nasa_table")
data class NasaImageData (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name ="id")
    val id: Int,
    @ColumnInfo(name ="copyright")
    @SerializedName("copyright") val copyright : String?,
    @ColumnInfo(name ="date")
    @SerializedName("date") val date : String,
    @ColumnInfo(name ="explanation")
    @SerializedName("explanation") val explanation : String,
    @ColumnInfo(name ="hdurl")
    @SerializedName("hdurl") val hdurl : String?,//There seems to be empty url
    @ColumnInfo(name ="media_type")
    @SerializedName("media_type") val media_type : String,
    @ColumnInfo(name ="service_version")
    @SerializedName("service_version") val service_version : String,
    @ColumnInfo(name ="title")
    @SerializedName("title") val title : String,
    @ColumnInfo(name ="url")
    @SerializedName("url") val url : String?
) : Parcelable
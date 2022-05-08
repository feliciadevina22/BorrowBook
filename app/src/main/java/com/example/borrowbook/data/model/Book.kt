package com.example.borrowbook.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import java.util.*

@Parcelize
open class Book : Parcelable {
    @SerializedName("title")
    @IgnoredOnParcel
    var title : String = ""

    @SerializedName("isbn")
    @IgnoredOnParcel
    var isbn : String = ""

    @SerializedName("pageCount")
    @IgnoredOnParcel
    var pageCount : Int = 0

    @SerializedName("publishedDate")
    @IgnoredOnParcel
    val publishedDate : PublishedDate = PublishedDate()

    @SerializedName("thumbnailUrl")
    @IgnoredOnParcel
    var thumbnailUrl : String = ""

    @SerializedName("shortDescription")
    @IgnoredOnParcel
    var shortDescription : String = ""

    @SerializedName("longDescription")
    @IgnoredOnParcel
    var longDescription : String = ""

    @SerializedName("status")
    @IgnoredOnParcel
    var status : String = ""

    @SerializedName("authors")
    @IgnoredOnParcel
    var authors : List<String> = arrayListOf()

    @SerializedName("categories")
    @IgnoredOnParcel
    var categories : List<String> = arrayListOf()
}

class PublishedDate {
    @SerializedName("date")
    var date = Date()
}

@Parcelize
@Entity(tableName = "book_table")
data class BookLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title : String,
    var isbn : String,
    var page_count : Int,
    var thumbnail_url : String,
    var short_description : String,
    var long_description : String,
    var authors : String,
    var loan_id : String?
) : Parcelable
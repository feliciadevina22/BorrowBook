package com.example.borrowbook.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "loan_table")
data class Loan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val user_id: String,
    val book_id: Int,
    val loan_date: String,
    val due_date: String,
    var return_date: String?
) : Parcelable
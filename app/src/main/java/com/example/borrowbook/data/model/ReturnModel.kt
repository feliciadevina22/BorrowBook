package com.example.borrowbook.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllRecord(
    @Embedded
    val record: Loan,

    @Relation(
        entity = BookLocal::class,
        parentColumn = "book_id",
        entityColumn = "id",
    )
    val book: BookLocal,

    @Relation(
        entity = User::class,
        parentColumn = "user_id",
        entityColumn = "id")
    val user: User,
) : Parcelable
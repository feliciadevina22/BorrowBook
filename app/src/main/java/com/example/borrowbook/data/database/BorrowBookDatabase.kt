package com.example.borrowbook.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.borrowbook.data.model.BookLocal
import com.example.borrowbook.data.model.Loan
import com.example.borrowbook.data.model.User
import com.example.borrowbook.data.rest.BookDao
import com.example.borrowbook.data.rest.LoanDao
import com.example.borrowbook.data.rest.UserDao

@Database(entities = [Loan::class, BookLocal::class, User::class], version = 2, exportSchema = false)
abstract class BorrowBookDatabase : RoomDatabase() {

    abstract fun loanDao(): LoanDao
    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: BorrowBookDatabase? = null

        fun getDatabase(context: Context): BorrowBookDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            kotlin.synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BorrowBookDatabase::class.java,
                    "borrow_book_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.kurilov.deadspase.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kurilov.deadspase.data.db.dao.*
import com.kurilov.deadspase.data.db.entry.*

@Database(
    entities = [
        Building::class,
        Deadline::class,
        Exam::class,
        Group::class,
        SchedulePair::class,
        SemesterInfo::class,
        Teacher::class,
               ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDAO

    abstract fun deadlineDao(): DeadlineDAO

    abstract fun examDao(): ExamDAO

    abstract fun groupDao(): GroupDAO

    abstract fun schedulePairDao(): SchedulePairDAO

    abstract fun semesterInfoDao(): SemesterInfoDAO

    abstract fun teacherDao(): TeacherDAO

    companion object {

        private const val DATABASE_NAME = "appdatabase.db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationOnDowngrade()
                .build()
        }
    }
}
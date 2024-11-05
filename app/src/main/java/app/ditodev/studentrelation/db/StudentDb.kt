package app.ditodev.studentrelation.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import app.ditodev.studentrelation.dao.StudentDao
import app.ditodev.studentrelation.entity.Course
import app.ditodev.studentrelation.entity.Student
import app.ditodev.studentrelation.entity.University

@Database(
    entities = [Student::class, University::class, Course::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = StudentDatabase.AutoMigration::class)
    ],
    exportSchema = true,
)
abstract class StudentDatabase : RoomDatabase() {

    @RenameColumn(tableName = "Student", fromColumnName = "graduate", toColumnName = "isGraduate")
    class AutoMigration : AutoMigrationSpec

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StudentDatabase {
            if (INSTANCE == null) {
                synchronized(StudentDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java, "student_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as StudentDatabase
        }
    }
}
package app.ditodev.studentrelation

import android.content.Context
import app.ditodev.studentrelation.db.StudentDatabase

object DependencyInjection {
    fun provideStudentRepository(context:Context): StudentRepository {
        val database = StudentDatabase.getDatabase(context)
        return StudentRepository(database.studentDao())
    }
}
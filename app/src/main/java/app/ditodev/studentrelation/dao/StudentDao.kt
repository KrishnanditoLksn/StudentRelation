package app.ditodev.studentrelation.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import app.ditodev.studentrelation.entity.Course
import app.ditodev.studentrelation.entity.Student
import app.ditodev.studentrelation.entity.StudentAndUniversity
import app.ditodev.studentrelation.entity.University
import app.ditodev.studentrelation.entity.UniversityAndStudent

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: List<Student>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUniversity(university: List<University>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(course: List<Course>)

    @RawQuery(observedEntities = [Student::class])
    fun getAllStudent(query: SupportSQLiteQuery): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM student")
    fun getAllStudentUniversityList(): LiveData<List<StudentAndUniversity>>

    @Transaction
    @Query("SELECT * FROM university")
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>>
}
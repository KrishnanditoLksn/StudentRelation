package app.ditodev.studentrelation.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
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

    @Query("SELECT * from student")
    fun getAllStudent(): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM student")
    fun getAllStudentUniversityList(): LiveData<List<StudentAndUniversity>>

    @Transaction
    @Query("SELECT * FROM university")
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>>
}
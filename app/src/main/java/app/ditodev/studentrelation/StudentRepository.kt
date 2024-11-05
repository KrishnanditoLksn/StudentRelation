package app.ditodev.studentrelation

import androidx.lifecycle.LiveData
import app.ditodev.studentrelation.dao.StudentDao
import app.ditodev.studentrelation.entity.Student
import app.ditodev.studentrelation.entity.StudentAndUniversity
import app.ditodev.studentrelation.entity.UniversityAndStudent

class StudentRepository(private val studentDao: StudentDao) {
    fun getAllStudent(): LiveData<List<Student>> = studentDao.getAllStudent()

    suspend fun insertAllData() {
        studentDao.insertStudent(InitialDataSource.getStudents())
        studentDao.insertUniversity(InitialDataSource.getUniversities())
        studentDao.insertCourse(InitialDataSource.getCourses())
    }

    fun getAllStudentAndUniversity():LiveData<List<StudentAndUniversity>> = studentDao.getAllStudentUniversityList()
    fun getAllUniversityAndStudent():LiveData<List<UniversityAndStudent>> = studentDao.getAllUniversityAndStudent()
}
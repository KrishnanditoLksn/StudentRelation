package app.ditodev.studentrelation

import androidx.lifecycle.LiveData
import app.ditodev.studentrelation.dao.StudentDao
import app.ditodev.studentrelation.entity.Student
import app.ditodev.studentrelation.entity.StudentAndUniversity
import app.ditodev.studentrelation.entity.UniversityAndStudent
import app.ditodev.studentrelation.helper.SortType
import app.ditodev.studentrelation.helper.SortUtils

class StudentRepository(private val studentDao: StudentDao) {
    fun getAllStudent(sortType: SortType): LiveData<List<Student>> {
        val query = SortUtils.getSortedQuery(sortType)
        return studentDao.getAllStudent(query)
    }

    fun getAllStudentAndUniversity(): LiveData<List<StudentAndUniversity>> =
        studentDao.getAllStudentUniversityList()

    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>> =
        studentDao.getAllUniversityAndStudent()
}
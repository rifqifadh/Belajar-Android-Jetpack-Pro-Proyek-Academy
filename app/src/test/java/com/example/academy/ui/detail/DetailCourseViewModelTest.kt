package com.example.academy.ui.detail

import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test

class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private lateinit var dummyCourse: CourseEntity

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel()
        dummyCourse = CourseEntity("a14",
            "Menjadi Android Developer Expert",
            "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
            "100 Hari",
            false,
            "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourse() {
        viewModel.setCourseId(dummyCourse.courseId)
        val courseEntity: CourseEntity = viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.title, courseEntity.title)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
    }

    @Test
    fun getModules() {
        val courseEntities = viewModel.getModules()
        viewModel.setCourseId(dummyCourse.courseId)
        assertNotNull(courseEntities)
        assertEquals(7, viewModel.getModules().size)
    }
}
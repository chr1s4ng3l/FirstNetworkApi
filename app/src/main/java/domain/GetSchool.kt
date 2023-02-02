package domain

import com.example.firstnetworkapi.model.School
import com.example.firstnetworkapi.service.SchoolRepository
import javax.inject.Inject

class GetSchool @Inject constructor(private val repository: SchoolRepository) {

    suspend operator fun invoke(): List<School>? = repository.getAllSchool()
}
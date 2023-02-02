package domain

import com.example.firstnetworkapi.model.School
import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.service.SchoolRepository
import com.example.firstnetworkapi.service.SchoolService
import javax.inject.Inject

class GetSchool @Inject constructor(private val repository: SchoolRepository) {

    suspend operator fun invoke(): List<SchoolsItem> = repository.getAllSchool()
}
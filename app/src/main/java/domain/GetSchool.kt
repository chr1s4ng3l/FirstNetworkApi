package domain

import com.example.firstnetworkapi.model.SchoolProvider
import com.example.firstnetworkapi.model.SchoolsItem
import javax.inject.Inject

class GetSchool @Inject constructor(private val provider: SchoolProvider) {

    suspend operator fun invoke(): List<SchoolsItem> = provider.schools
}
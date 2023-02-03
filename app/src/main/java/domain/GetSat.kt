package domain

import com.example.firstnetworkapi.model.SatItem
import com.example.firstnetworkapi.model.SatProvider
import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.service.SchoolRepository
import javax.inject.Inject

class GetSat @Inject constructor(private val repo: SchoolRepository) {

    suspend operator fun invoke(): List<SatItem>? = repo.getAllSat()


}
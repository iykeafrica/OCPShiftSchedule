package com.i_africa.shiftcalenderobajana.networking

import com.i_africa.shiftcalenderobajana.network_api.GoogleFormApiGet
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchVersionNameCodeUseCase @Inject constructor(private val googleFormApiGet: GoogleFormApiGet) {

    sealed class Result {
        data class Success(val versionNameCode: List<List<String>>) : Result()
        data class Failure(val responseCode: Int) : Result()
    }

    suspend fun fetchVersionCodeName() : Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = googleFormApiGet.getVersionNameCode()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.values)
                } else {
                    return@withContext Result.Failure(response.code())
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure(503)
                } else {
                    throw t
                }
            }
        }
    }
}
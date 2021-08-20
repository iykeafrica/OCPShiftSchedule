package com.i_africa.shiftcalenderobajana.networking

import com.i_africa.shiftcalenderobajana.network_api.GoogleFormApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubmitFormUseCase @Inject constructor(private val googleFormApi: GoogleFormApi) {

    sealed class Result {
        data class Success(val responseCode: Int) : Result()
        data class Failure(val responseCode: Int) : Result()
    }

    suspend fun submitFCM(fcmToken: String) : Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = googleFormApi.sendFCMToken(fcmToken)
                if (response.isSuccessful) {
                    return@withContext Result.Success(response.code())
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
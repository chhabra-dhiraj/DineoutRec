package io.github.chhabra_dhiraj.dineoutrec.data.util

import io.github.chhabra_dhiraj.dineoutrec.domain.util.DataError
import io.github.chhabra_dhiraj.dineoutrec.domain.util.Result
import retrofit2.HttpException
import java.io.IOException

fun <D> Exception.getDataExceptionError(): Result<D, DataError.Network> {
    this.printStackTrace()
    return when (this) {
        is IOException -> Result.Error(DataError.Network.NO_INTERNET)
        is HttpException -> {
            when (this.code()) {
                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }

        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}
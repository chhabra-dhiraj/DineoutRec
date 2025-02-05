package io.github.chhabra_dhiraj.dineoutrec.domain.util

sealed interface DataError : Error {
    // TODO: Decide which other errors to add at the end
    // Handling below 3 errors, for now
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        UNKNOWN
    }
}
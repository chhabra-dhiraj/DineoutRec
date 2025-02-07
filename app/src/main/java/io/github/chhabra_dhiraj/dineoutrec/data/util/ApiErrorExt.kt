package io.github.chhabra_dhiraj.dineoutrec.data.util

// TODO: Revisit this file

/** Error message for json serialization.
 * Imp** - Caller must be a jsonElement */
fun Any.getSerializationExceptionError(jsonObjectName: String) =
    "JsonObject '$jsonObjectName' expected, but got ${this::class}"

// Error message for unrecognised data transfer object
fun Any.getUnrecognisedClassError() =
    "'${this::class}' expected, but got something else"
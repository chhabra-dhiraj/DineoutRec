package io.github.chhabra_dhiraj.dineoutrec.data.util

/** Error message for json serialization exception
 **/
fun String.getSerializationExceptionError() =
    "Json field '$this' expected, but it was not found"
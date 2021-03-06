package ru.leonov.vktrainingclient.mvp.utils

private const val ParamEnd = "&"
private const val equalSign = "="

fun String.getParameter(paramName: String) : String? {

    val leftPartTrimmed = this.substringAfter(paramName+equalSign, "")
    val paramValue = leftPartTrimmed.substringBefore(ParamEnd, leftPartTrimmed)

    return if (paramValue.isNotEmpty()) paramValue else null
}

fun concatString(vararg values: String?): String {
    var res = ""
    values.forEach {
        it?.let { res += it}
    }
    return res
}
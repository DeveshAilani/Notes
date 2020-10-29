package com.mad.notes.utils

fun isNullOrEmpty(collection: Collection<Any>?): Boolean {
    return (null == collection || collection.isEmpty())
}

fun isNullOrEmpty(string: String?): Boolean {
    return string.isNullOrEmpty() || string.isBlank()
}

fun isNullOrEmpty(map: Map<Any, Any?>?): Boolean {
    return (null == map || map.isEmpty())
}

fun isNullOrEmpty(any: Any?): Boolean {
    return (null == any)
}
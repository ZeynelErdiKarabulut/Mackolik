package com.zeynelerdi.mackolik.util.extension

/**Examples**/
/**
 *  var list?:List = null
 *
 *  list.isNullOrEmpty() -> true
 *  list = List<T>()
 *
 *  list.isNullOrEmpty() -> true
 *
 *  list = list.plus(T())
 *  list.isNullOrEmpty() -> false
 */
fun <T> List<T>?.isNullOrEmpty(listener: ReturnListener): Boolean {
    if (!this.isNull() && this!!.isNotEmpty()) {
        listener.onFalse()
    } else {
        listener.onTrue()
    }

    return this.isNullOrEmpty()
}

/**Examples**/
/**
 * [1,42,12,54,64,12,2] -> list
 *
 * list.size -> 7
 * list.midIndex() -> 3
 *
 * list.subList(0,lastIndex-1).size -> 6
 * list.midIndex() -> 3
 */
val <T> List<T>.midIndex: Int
    get() = if (this.isNullOrEmpty()) 0 else size / 2

/**Examples**/
/**
 * [1,42,12,54,64,12,2] -> list
 *
 * list.size -> 7
 * list.midElement() -> 54
 */
fun <T> List<T>.midElement(): T? {
    return if (this.isNullOrEmpty())
        null else
        this[size / 2]
}

/**Examples**/
/**
 * [1,42,12,54,64,12,2] -> list
 *
 * list.addition() -> 1+42+12+54+64+12+2 = 187
 *
 * if list is null or empty, return 0
 */
fun List<Int>?.addition(): Int {
    return if (this.isNullOrEmpty()) 0
    else {
        var total = this[0]
        for (i in 1 until this.size) {
            total += get(i)
        }
        total
    }
}

/**Examples**/
/**
 * [1,42,12,54,64,12,2] -> list
 *
 * list.subtraction() -> -1-42-12-54-64-12-2 = -187
 *
 * if list is null or empty, return 0
 */
fun List<Int>?.subtraction(): Int {
    return if (this.isNullOrEmpty()) 0
    else {
        var total = 0

        for (i in this.indices) {
            total += get(i)
        }

        total * -1
    }
}

/**Examples**/
/**
 * [1,42,12,54,64,12,2] -> list
 *
 * list.multiplication() -> 1*42*12*54*64*12*2 = 41803776
 *
 * * if list is null or empty, return 0
 */
fun List<Int>?.multiplication(): Int {
    return if (this.isNullOrEmpty()) 0
    else {
        var total = this[0]

        for (i in 1 until this.size) {
            total *= get(i)
        }
        total
    }
}

/**Examples**/
/**
 * [1,42,12,54,64,12,2] -> list
 *
 * list.divide() -> 1/(1*42*12*54*64*12*2) = 2.3921284e-8
 *
 * if list is null or empty, return 0
 */
fun List<Int>?.divide(): Int {
    return if (this.isNullOrEmpty()) 0
    else {
        var total = 1

        for (i in this.indices) {
            total /= get(i)
        }
        total
    }
}


/**Examples**/
/**
 *
 * var p0 = Person( id = 0, name = "a")
 * var p1 = Person( id = 1, name = "b")
 * var p2 = Person( id = 2, name = "c")
 * var p3 = Person( id = 3, name = "d")
 *
 * [p0,p1,p2,p3] -> list
 *
 * list.swap(p0,p2) -> [p2,p1,p0,p3]
 * list.swap(0,2)   -> [p0,p1,p2,p3]
 *
 * it runs as hashcode
 *
 * list is not null
 * if list is empty, return an empty list
 *
 * parameters ->
 * o1 -> T
 * o2 -> T
 *
 * both parameters should be created from the same class.
 */
fun <T> List<T>.swap(o1: T, o2: T): List<T> {
    var list = List<T>(this.size) {
        return listOf(it as T)
    }

    for (i in this.indices) {
        list = when {
            get(i).hashCode() == o1.hashCode() -> list.plus(o2)
            get(i).hashCode() == o2.hashCode() -> list.plus(o1)
            else -> list.plus(get(i))
        }
    }

    return list
}

/**Examples**/
/**
 * ['a','ert','ba  cd'] -> list
 *
 * list.sumAllChar() -> aertba  cd
 *
 * if list is null or empty, return an empty string
 */
fun List<String>?.sumAllChar(): String {
    return if (this.isNullOrEmpty()) ""
    else {
        var total = this[0]

        for (i in 1 until this.size) {
            total += get(i)
        }
        total
    }
}

/**Examples**/
/**
 * [null,"a","b",null,""] -> list
 *
 * list.getAreNulls() -> [null,null]
 *
 * if list is null or empty, return the same list
 */
fun <T> List<T?>?.getAreNulls(): List<T?>? {
    if (this.isNullOrEmpty()) return this

    var list = List<T?>(this.size) {
        return this
    }

    this.forEach {
        it?.apply { list = list.plus(this) }
    }

    return list
}

/**Examples**/
/**
 * ["abc"","a","b","c","b","","d","","a",] -> list
 *
 * list.duplicate() -> ["abc"","a","b","c","","d"]
 *
 * if list is null or empty, return the same list
 */
fun <T> List<T>?.duplicate(): List<T>? {
    if (this.isNullOrEmpty()) return this

    var list = List<T>(this.size) {
        return this
    }

    this.forEach {
        if (!list.contains(it)) {
            list = list.plus(it)
        }
    }

    return list
}
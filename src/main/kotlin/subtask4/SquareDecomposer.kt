package subtask4

import kotlin.math.sqrt

class SquareDecomposer {

    private fun calcProduct(curArr: MutableList<Int>): Int {
        var res = 0
        for (i in curArr.indices) {
            res += curArr[i] * curArr[i]
        }
        return res
    }

    private fun recurFinder(total: Int, current: MutableList<Int>): MutableList<Int>? {
        val sum = calcProduct(current)
        when {
            sum > total -> {
                return null
            }
            sum == total -> {
                return current
            }
            else -> {
                var newInt =
                    minOf((sqrt((total - sum).toDouble())), (current.last() - 1).toDouble()).toInt()
                var newArr: MutableList<Int>?
                while (newInt > 0) {
                    newArr = mutableListOf<Int>().apply { addAll(current) }
                    newArr.add(newInt)
                    newArr = recurFinder(total, newArr)
                    if (newArr != null) {
                        return newArr
                    }
                    newInt--
                }
                return null
            }
        }
    }

    fun decomposeNumber(number: Int): Array<Int>? {
        if (number < 0) {
            return null
        }
        var resultArr: MutableList<Int>? = mutableListOf()
        var startVal = number - 1
        while (startVal > 0) {
            resultArr = recurFinder(number * number, mutableListOf(startVal))
            if (resultArr != null) {
                break
            }
            startVal--
        }
        resultArr?.sort()
        return resultArr?.toTypedArray()
    }
}
package subtask3

class ArrayCalculator {

    fun maxProductOf(numberOfItems: Int, itemsFromArray: Array<Any>): Int {
        val digits = mutableListOf<Int>()
        for (i in itemsFromArray.indices) {
            if (itemsFromArray[i]::class == Int::class) {
                digits.add(itemsFromArray[i] as Int)
            }
        }
        val sorted = digits.sortedBy { -kotlin.math.abs(it) }
        if (sorted.count() == 0) {
            return 0
        }
        var product = 1
        var minNegative = 0
        var minPositive = 0
        for (i in sorted.indices) {
            val element = sorted[i]
            if (i >= numberOfItems) {
                product = if (product < 0 && element > 0) {
                    product * element / minNegative
                } else if (product < 0 && element < 0) {
                    product * element / minPositive
                } else {
                    break
                }
            } else {
                product *= element
                if (element < 0) {
                    if (minNegative == 0 || element > minNegative) {
                        minNegative = element
                    }
                } else {
                    if (minPositive == 0 || element < minPositive) {
                        minPositive = element
                    }
                }
            }
        }
        return product
    }
}
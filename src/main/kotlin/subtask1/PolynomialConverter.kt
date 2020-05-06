package subtask1

class PolynomialConverter {

    fun convertToStringFrom(numbers: Array<Int>): String? {
        if (numbers.count() == 0) {
            return null
        }
        var result = ""
        val len = numbers.count()
        for (i in numbers.indices) {
            val number = numbers[i]
            if (number == 0) {
                continue
            }
            val sign = if (number < 0) " - " else (if (i == 0) "" else " + ")
            val member = if (kotlin.math.abs(number) > 1) kotlin.math.abs(number).toString() else ""
            val x = if (i < len - 1) "x" else ""
            val deg = if (i < len - 2) "^" + (len - i - 1).toString() else ""
            result = result + sign + member + x + deg
        }
        return result
    }
}

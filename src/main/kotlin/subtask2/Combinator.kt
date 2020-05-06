package subtask2

class Combinator {

    private fun f(v: Int): Long {
        return if (v < 2) {
            1
        } else {
            v * f(v - 1)
        }
    }

    fun checkChooseFromArray(array: Array<Int>): Int? {
        if (array.count() < 2) {
            return null
        }
        val c = array[0]
        val n = array[1]
        var k = 1
        var res: Long
        do {
            if (k > n) {
                return null
            }
            res = f(n) / (f(k) * f(n - k))
            k++
        } while (res < c)
        return (k - 1)
    }
}

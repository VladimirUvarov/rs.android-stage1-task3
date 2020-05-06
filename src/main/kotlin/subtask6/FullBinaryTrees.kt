package subtask6

import java.io.ByteArrayOutputStream
import java.io.ByteArrayInputStream
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import kotlin.math.pow

class FullBinaryTrees {

    private fun <T : MutableList<MutableList<String>>> deepCopy(obj: T): T {
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(obj)
        oos.close()
        val bais = ByteArrayInputStream(baos.toByteArray())
        val ois = ObjectInputStream(bais)
        @Suppress("unchecked_cast")
        return ois.readObject() as T
    }

    var arrayOfTrees = mutableListOf<MutableList<MutableList<String>>>()

    private fun printTree(tree: MutableList<MutableList<String>>): String {
        var res = "["
        var nullCumul = ""
        for (i in tree.indices) {
            val level = tree[i]
            for (j in level.indices) {
                val element = level[j]
                if (i > 0 && tree[i - 1][j / 2] == "null") {
                    continue
                }
                if (element == "0") {
                    res += nullCumul
                    nullCumul = ""
                    res += element
                    res = "$res,"
                } else {
                    nullCumul += element
                    nullCumul = "$nullCumul,"
                }
            }
        }
        res = res.replaceRange(res.length - 1, res.length, "]")
        return res
    }

    private fun printTrees(trees: MutableList<MutableList<MutableList<String>>>): String {
        var res = "["
        for (i in trees.indices) {
            res += printTree(trees[i])
            res = "$res,"
        }
        res = res.replaceRange(res.length - 1, res.length, "]")
        return res
    }

    private fun buildTree(
        sourceTree: MutableList<MutableList<String>>,
        nodes: Int,
        totalNodes: Int
    ) {
        if (totalNodes == nodes) {
            arrayOfTrees.add(sourceTree)
        } else {
            for (i in sourceTree.indices) {
                val level = sourceTree[i]
                for (j in level.indices) {
                    val element = level[j]
                    if (element == "0" &&
                        (i == sourceTree.count() - 1 || (sourceTree[i + 1][2 * j] == "null"))
                    ) {
                        val newTree = deepCopy(sourceTree)
                        if (i == sourceTree.count() - 1) {
                            val newLevel = mutableListOf<String>()
                            val p = 2.toDouble().pow(sourceTree.count()).toInt() - 1
                            for (u in 0..p) {
                                newLevel.add("null")
                            }
                            newTree.add(newLevel)
                        }
                        newTree[i + 1][2 * j] = "0"
                        newTree[i + 1][2 * j + 1] = "0"
                        buildTree(newTree, (nodes + 2), totalNodes)
                    }
                }
            }
        }
    }

    fun stringForNodeCount(count: Int): String {
        if (count % 2 > 0) {
            val core = mutableListOf<MutableList<String>>().apply { add(mutableListOf("0")) }
            buildTree(core, 1, count)
            return printTrees(arrayOfTrees)
        }
        return "[]"
    }
}
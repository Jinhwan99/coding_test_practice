package programmers._258711

class Solution {
    enum class Type {
        Zero, // 도넛
        One, // 막대
        Eight // 8자
    }

    class Node {
        var indegree = 0
        val outputs = mutableListOf<Int>()
    }

    private val graph = mutableMapOf<Int, Node>()

    fun solution(edges: Array<IntArray>): IntArray {
        val answer = IntArray(4)

        edges.forEach { (a, b) ->
            graph.getOrPut(a) {Node()}.apply { outputs.add(b) }
            graph.getOrPut(b) {Node()}.apply { indegree++ }
        }

        graph.forEach { (i, n) ->
            if (n.outputs.size > n.indegree + 1) {
                answer[0] = i
                n.outputs.forEach {
                    when (determineGraph(it)) {
                        Type.Zero -> answer[1]++
                        Type.One -> answer[2]++
                        Type.Eight -> answer[3]++
                    }
                }
                return@forEach
            }
        }

        return answer
    }

    private fun determineGraph(initialVertex: Int): Type {
        var currentNode = graph[initialVertex]!!
        while (true) {
            when (currentNode.outputs.size) {
                2 -> return Type.Eight
                0 -> return Type.One
                else -> {
                    val nextVertex = currentNode.outputs[0]
                    if (initialVertex == nextVertex) return Type.Zero
                    currentNode = graph[nextVertex]!!
                }
            }
        }
    }
}
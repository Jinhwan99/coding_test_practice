# 2024 KAKAO WINTER INTERNSHIP

## [도넛과 막대 그래프](https://school.programmers.co.kr/learn/courses/30/lessons/258711)

### 문제 분석

각 그래프의 특징을 찾아보자.

도넛모양 그래프는 모든 노드들의 indegree와 outdegree가 1이다.  
8자모양 그래프는 중앙의 노드는 indegree와 outdegree가 2이고 나머지 노드들은 indegree와 outdegree가 1이다.  
막대 모양 그래프의 경우 시작노드는 outdegree 1, 끝 노드는 indegree 1이고 나머지 노드들은 indegree와 outdegree 둘다 1이다.

문제의 제한사항중 그래프의 갯수가 2개 이상이라는 조건으로부터 추가된 정점은 outdegree가 2이상으로 유일하게 결정되는것을 알 수 있다.

그렇게 정해진 정점으로부터 출발한 모든 노드들이 개별 그래프의 한 노드이고, 해당 노드들로부터 이어진 간선을 탐색함으로써 해당 그래프가 어떤 모양인지 알아낼 수 있다.

이어진 간선을 탐색하다 outdegree가 2인 노드를 만나면 해당 그래프는 8자모양, outdegree가 0인 노드를 만다면 막대모양, 시작 노드로 돌아오게되면 도넛모양으로 구분할 수 있다.

---

### 설계 및 해결

입력으로 들어오는 edge들이 정렬되지 않은 상태로 들어오기 때문에 전체 정점의 갯수를 알지 못한다. 따라서 정점의 번호를 key로 하는 map을 생성하고 value로 그래프를 탐색할 수 있게하는 간선 정보와, 시작 정점을 찾을 수 있게하는 indegree를 저장할 수 있는 Node타입을 추가했다.

```kt
    class Node {
        var indegree = 0
        val outputs = mutableListOf<Int>()
    }

    private val graph = mutableMapOf<Int, Node>()
```

이후 입력받은 edges를 순회하며 graph를 완성시킨다.

완성된 graph를 순회하며 추가된 정점을 찾고  
해당 정점으로부터 출발하는 간선들에 해당하는 그래프를 판별하는 함수를 구현했다.

```kt
    enum class Type {
        Zero, // 도넛
        One, // 막대
        Eight // 8자
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
```


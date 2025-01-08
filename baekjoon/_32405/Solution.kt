package baekjoon._32405

class Player(val id: Int, val atk: Int, val hp: Int)

val readIntList = { readln().split(" ").map { it.toInt() } }
fun main() {
    val n = readln().toInt()
    var players = readIntList().zip(readIntList()).mapIndexed { i, (atk, hp) -> Player(i+1, atk, hp) }

    var atkSum = 0
    var loop = 0

    while (players.size != 1) {
        players = players.filter { player ->
            if (player.hp > atkSum - player.atk * loop) {
                atkSum += player.atk
                true
            } else false
        }
        loop++
    }
    print(players[0].id)
}
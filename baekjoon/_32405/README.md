# 2024 충남대학교 SW-IT Contest

## [배틀 로얄](https://www.acmicpc.net/problem/32405)

### 문제 분석

간단히 생각하면 매턴 플레이어의 공격력 만큼 다른 플레이어들의 체력을 감소시키고, 남은 체력이 0 이하인 플레이어들은 탈락시키며 한명이 남을때까지 반복하면 될것 같다.

하지만 해당 방식으로는 매턴 최대 20만명의 플레이어의 체력을 줄이고 확인하는 과정이 필요해 1번부터 n번 플레이어까지 한번씩 턴이 진행될때 필요한 시간복잡도가 O(n^2)이다.

해당 방식으로는 제한시간 안에 문제를 해결할 수 없을것으로 보여 시간을 줄일 방법에 대해 고민해보았다.  
매 턴 자기자신이 살아남았는지 확인하고, 살아남은 플레이어끼리 다음 게임을 진행하게 하면 n명의 플레이어가 한번씩 턴을 진행할때 시간복잡도를 O(n)으로 줄일 수 있을것으로 보였다.

---

### 설계 및 해결

각 플레이어의 턴에 자기자신이 살았는지 확인하기 위해서는 자신의 턴 이전까지 얼마의 데미지를 입었는지 계산할 필요가 있다.

턴을 진행한 플레이어들의 공격력을 가산한 누적합을 계산하는데, 플레이어가 입을 데미지는 자신의 공격력을 제외해야 했으므로 현재 자신이 몇번의 공격을 수행했는지 추가로 카운트 할 필요가 있다.

살아남은 플레이어들을 순회하며 체력이 (데미지 누계 - 공격력 * 공격횟수) 보다 큰 플레이어가 존재하면 데미지 누계에 가산하고, 다음게임에 참여하게 하기를 반복해 마지막 한명이 남을때까지 반복을 수행했다.
```kt
    while (players.size != 1) {
        players = players.filter { player ->
            if (player.hp > atkSum - player.atk * loop) {
                atkSum += player.atk
                true
            } else false
        }
        loop++
    }
```
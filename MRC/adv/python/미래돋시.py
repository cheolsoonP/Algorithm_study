"""

1~N번 회사
1번 -> K번 -> X번 순서로 이동이 목적
각 회사가 연결되어 있다면 1의 시간으로 이동 가능


"""

n, m = map(int, input().split())
INF = 1e9
graph = [[INF for _ in range(n+1)] for _ in range(n+1)]
for i in range(1, n+1):
    for j in range(1, n+1):
        if i == j:
            graph[i][j] = 0

# A와 B가 서로 가는 비용은 1로 설정
for i in range(m):
    a, b = map(int, input().split())
    graph[a][b] = 1
    graph[b][a] = 1

x, k = map(int, input().split())

# 1번 -> K번 > X번으로 이동해야 한다.

# 점화식에 따라 플로이드 워셜 알고리즘 수행
for k in range(1, n+1):
    for a in range(1, n+1):
        for b in range(1, n+1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

distance = graph[1][k] + graph[k][x]

if distance >= INF:
    print("-1")
else:
    print(distance)
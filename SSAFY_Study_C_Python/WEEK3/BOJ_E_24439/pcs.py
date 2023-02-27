"""
[BOJ] 24439 바리스타

n m 미로
1,1 ~ n,m
가장 적은 횟수로 이동
상하좌우 이동 가능
벽이 있으면 이동 불가능
바리스타가 희망 - 바리스타의 힘
상하좌우 - 방향 설정 - 기술 해당 방향 모든 칸 벽 없어짐
기술 최대 한번 사용 가능


"""

n, m = map(int, input().split())
graph = []
for i in range(n):
    graph.append(list(map(int, input())))
print(graph)
x, y = 0, 0


def skill(cx, cy, dir):
    for i in range(cx, n):
        for j in range(cy, m):
            if graph[i][j] == 1:
                graph[i][j] = 0

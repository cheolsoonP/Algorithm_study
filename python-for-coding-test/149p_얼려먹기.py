"""
149p 얼려먹기
N x M 얼음틀

구멍 - 0
칸막이 - 1
생성되는 총 아이스크림수를 구해라
0인 부분이 연결된 곳이 아이스크림

DFS문제
1. 특정 지점, 상하좌우  주변 지점 중 0인 값 중에 아직 방문하지 않은 지점 ->  해당 지점 방문
2. 방문한 지점에서 다시 상하좌우 살펴보면서 방문을 진행 계속 진행


"""

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input())))

def dfs(x, y):
    # 범위 벗어나면 종료
    if x <= -1 or x >= n or y <= -1 or y >= m:
        return False

    if graph[x][y] == 0:
        graph[x][y] = 1
        dfs(x, y+1)
        dfs(x, y-1)
        dfs(x-1, y)
        dfs(x+1, y)
        return True
    return False

result = 0
for i in range(n):
    for j in range(m):
        if dfs(i, j) == True:
            result += 1

print(result)

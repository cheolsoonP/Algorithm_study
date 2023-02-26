from collections import deque

T = int(input())
n, m = map(int, input().split())

devQ = deque()
suQ = deque()
graph = []
for i in range(n):
    graph.append(list(input()))
    for j in range(m):
        if graph[i][j] == '*':
            devQ.append((i,j))
        if graph[i][j] == 'S':
            suQ.append((i,j))
            graph[i][j] = '.'



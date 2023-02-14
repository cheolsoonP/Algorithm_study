

n, m = map(int, input().split())

graph = [[0 for _ in range(301)] for _ in range(301)]
candy = [[0 for _ in range(301)] for _ in range(301)]
for i in range(n):
    x, y = map(int, input().split())
    c = m-x-y
    if c <= 0:
        candy[x][y] = 0
    else:
        candy[x][y] = c

for i in range(301):
    for j in range(301):
        if i == 0 and j == 0:
            continue
        graph[i][j] = max(graph[i-1][j], graph[i][j-1]) + candy[i][j]

print(graph[300][300])
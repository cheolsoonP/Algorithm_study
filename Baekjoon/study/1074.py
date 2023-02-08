
"""
Z모양 탐색
2^n x 2^n 배열
Z모양 탐색

"""

def dfs(x, y):
    global r, c, count

    for x in range(i, i + 2):
        for y in range(j, j + 2):
            graph[x][y] = count
            count += 1
            if x == r and y == c:
                print(graph[r][c])
                return


n, r, c = map(int, input().split())
graph = [[0 for _ in range(0, 2**n)] for _ in range(0, 2**n)]
count = 0

gap = n**2

for i in range(0, 2**n, gap):
    for j in range(0, 2**n, gap):
        if i <= r < i+gap and j <= c < j+gap:
            dfs(i, j)
        else:
            count += 4


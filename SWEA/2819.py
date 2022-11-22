# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def dfs(x, y, letter):
    if len(letter) >= 7:
        letter_list.add(letter)
        return
    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        if nx < 0 or nx >= 4 or ny < 0 or ny >= 4:
            continue
        dfs(nx, ny, letter+graph[nx][ny])



T = int(input())

for test_case in range(1, T+1):
    graph = []
    letter_list = set()
    for i in range(4):
        graph.append(list(input().split()))

    for i in range(4):
        for j in range(4):
            dfs(i, j, graph[i][j])

    print("#"+str(test_case), len(letter_list))
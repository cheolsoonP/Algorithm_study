"""
[BOJ] 25331 Drop 7
23.03.09 pcs
"""
graph = []
for i in range(7):
    graph.append(list(map(int, input().split())))
n = int(input())
res = 1e9
# 공을 한번만 떨어뜨린다.
# 연속으로 터졌을 때 남아 있는 공의 최소 개수
def check_cnt(newGraph):
    global res
    cnt = 0
    for i in range(7):
        for j in range(7):
            if newGraph[i][j] > 0:
                cnt+=1
    res = min(res, cnt)


def pop_row(newGraph, x, y):
    q = deque((x, y))
    while q:
        x, y = q.popleft()
        for i in range(7):


# def pop_col(newGraph, x, y):


def check_bfs(x, y, newGraph):
    # 아래로 확인
    nx = x
    isCol = 0
    isRow = 0
    for i in range(7):
        nx += 1
        if 0 <= nx < 7 and newGraph[nx][y] > 0:
            isRow += 1
        else:
            break
    ny = y
    for i in range(7):
        ny += 1
        if 0 <= ny < 7 and newGraph[x][ny] > 0:
            isCol += 1
        else:
            break

    return isRow, isCol


def drop(col, num):
    newGraph = []
    pos = ()
    idx = -1
    for i in range(7):
        newGraph.append(list(graph[i]))
    for i in range(7):
        if newGraph[i][col] != 0:
            idx = i-1
            newGraph[i-1][col] = num
            break
        if i == 6:
            idx = i
            newGraph[i][col] = num

    q = deque()
    q.append(idx,col)
    while q:
        x, y = q.popleft()
        for i in range(4):


    return

for i in range(7):
    # 1열 ~ 7열까지 떨어뜨려본다.
    drop(i, n)

print(res)

"""

각 칸 - # 일 경우
상하좌우
    ?인 것 -> . 로 바꿔주기
    .인 것 -> pass
    # 같은 것이 존재 -> impossible

각 칸 - . 일 경우
상하좌우
    ?인 것 -> # 로 바꿔주기
    #인 것 -> pass
    . 같은 것이 존재 -> impossible

전체 통과 -> possible

"""


T = int(input())

# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y, curr_block):
    global check
    next_block = ''
    if curr_block == '.':
        next_block = '#'
    elif curr_block == '#':
        next_block = '.'

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        if graph[nx][ny] == curr_block:
            check = False
        if graph[nx][ny] == '?':
            graph[nx][ny] = next_block



# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    check = True
    n, m = map(int, input().split())

    graph = []
    for i in range(n):
        graph.append(list(input()))

    for i in range(n):
        for j in range(m):
            if graph[i][j] != '?':
                bfs(i, j, graph[i][j])

    if check == True:
        print("#"+str(test_case), "possible")
    else:
        print("#"+str(test_case), "impossible")
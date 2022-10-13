"""
backtracking
여왕이 서로 공격할 수 없는 위치에 놓는다.
대각선 가로 세로로 공격이 가능하다.


"""

n = int(input())

# col[0] = 1, 0번 행, 1번 열에 여왕있다. 행마다 하나의 여왕이 있을 수 있다.
col = [[] for _ in range(n)]
count = 0
visited = [[]for _ in range(n)]

def isAttack(curr_row):
    for prev_row in range(0, curr_row):
        # 세로 공격 가능한가
        if col[prev_row] == col[curr_row]:
            return True
        # 대각선 공격 가능한가
        if (curr_row - prev_row) == abs(col[prev_row] - col[curr_row]):
            return True
    return False

def dfs(row):
    global count

    # backtracking으로 pruning하기 가지치기
    # 만약 서로 공격이 된다면 종료
    # 행을 넘어 가면 종료.
    if row >= n:
        count += 1
        return

    for i in range(n):
        if visited[i]:
            continue
        col[row] = i  # row행 i번째에 여왕을 놓는다.
        # 공격이 불가능할 경우 진행
        if isAttack(row) == False:
            visited[i] = True
            dfs(row+1)
            visited[i] = False

visited = [False] * n
dfs(0)
print(count)



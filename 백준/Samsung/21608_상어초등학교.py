"""
21608 상어초등학교
nxn

학생수 n^2
자리를 정하는 날
1~n^2까지 번호 있다

학생의 순서를 정했고, 각 학생이 좋아하는 학생 4명을 조사했다
규칙대로 학생의 자리를 정할 것이다. 순서대로 정한다
abs(r1 - r2) + abs(c1 - c2) = 1을 만족하는 두칸 r1, c1 / r2, c2 인접
-> 상하좌우 가 근접이다.
1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다
2. 1을 만족하는 칸이 여러 개이면 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리 정한다
3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그런 칸도 여러개면
열 번호가 가장 작은 칸으로 정한다.

만족도를 구해라.
한 학생의 만족도는 인접한 칸에 앉은 좋아하는 학생의 수
0명 - 0점
1명 - 1점
2명 - 10점
3명 - 100점
4명 - 1000점

만족도의 총합을 구해라
"""

n = int(input())

graph = [[0] * n for _ in range(n)]
# 순서대로 주어진다. - 자리를 먼저정해야할 학생과 선호하는 학생 4명
favorite = []
for i in range(n * n):
    favorite.append(list(map(int, input().split())))

# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


# 자리 선정
def select_sit(student):
    friends = student[1:]
    final_x = -1
    final_y = -1

    # 비어있는 칸 중에 좋아하는 학생이 인접한 칸

    max_point = 0
    # 1. 좋아하는 학생이 가장 많은 곳
    for i in range(n):
        for j in range(n):
            count = 0
            empty = 0
            # 빈칸이면서, 상하좌우로 친구가 많은 곳
            if graph[i][j] == 0:
                for k in range(4):
                    nx, ny = i + dx[k], j + dy[k]
                    if 0 <= nx < n and 0 <= ny < n:
                        if graph[nx][ny] in friends:
                            count += 10
                        if graph[nx][ny] == 0:
                            empty += 1
            point = count + empty

            if max_point < point:
                max_point = point
                final_x, final_y = i, j

    if max_point == 0:
        for i in range(n):
            for j in range(n):
                if graph[i][j] == 0:
                    final_x, final_y = i, j
                    break

    return final_x, final_y


for i in range(n * n):
    x, y = select_sit(favorite[i])
    graph[x][y] = favorite[i][0]
result = 0

for i in range(n):
    for j in range(n):
        friends = []
        score = 0
        for k in range(n * n):
            if favorite[k][0] == graph[i][j]:
                friends = favorite[k][1:]
        for dir in range(4):
            nx, ny = i + dx[dir], j + dy[dir]
            if 0 <= nx < n and 0 <= ny < n:
                if graph[nx][ny] in friends:
                    score += 1
        if score == 1:
            result += 1
        elif score == 2:
            result += 10
        elif score == 3:
            result += 100
        elif score >= 4:
            result += 1000

print(result)
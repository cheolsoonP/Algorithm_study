# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


# 칸에 대해 상하좌우 확인
def check_score(x, y, st_like, graph, n):
    # score , 빈자리 - 10점, 좋아하는 사람 100점
    score = 0
    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            continue
        if graph[nx][ny] == -1:
            score += 10
        if graph[nx][ny] in st_like:
            score += 100
    return score


# 자리 결정, 좋아하는 사람이 많은 곳, 빈칸이 많은 곳
def choice_seat(s, graph, n):
    st_num = s[0]
    st_like = set(s[1:])
    max_score = -1e9
    x, y = 0, 0
    for i in range(n):
        for j in range(n):
            if graph[i][j] == -1:
                score = check_score(i, j, st_like, graph, n)
                if max_score < score:
                    x, y = i, j
                    max_score = score
    return x, y

def get_score(x, y, st_like, n):
    count = 0
    for i in range(4):
        nx, ny = x+dx[i], y+dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= n:
            continue
        if graph[nx][ny] in st_like:
            count += 1
    if count == 0:
        return 0
    if count == 1:
        return 1
    if count == 2:
        return 10
    if count == 3:
        return 100
    if count == 4:
        return 1000

def check_student_score(x, y, graph, student, n):
    for i in range(len(student)):
        if graph[x][y] == student[i][0]:
            score = get_score(x, y, student[i][1:], n)
            return score


n = int(input())
graph = [[-1 for _ in range(n)] for _ in range(n)]
# 0 - 학생, 1 2 3 4 좋아하는 학생
student = []
for i in range(n*n):
    student.append(list(map(int, input().split())))

for i in range(n*n):
    x, y = choice_seat(student[i], graph, n)
    graph[x][y] = student[i][0]

total = 0
for i in range(n):
    for j in range(n):
        total += check_student_score(i, j, graph, student, n)

print(total)
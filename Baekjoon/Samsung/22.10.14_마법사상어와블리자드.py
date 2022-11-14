
# 상1 하2 좌3 우4
dx = [0, -1, 1, 0, 0]
dy = [0, 0, 0, -1, 1]


def blizard(direction, distance):
    x, y = n//2, n//2

    for i in range(1, distance+1):
        nx, ny = x + dx[direction]*i, x + dy[direction]*i
        graph[nx][ny] = 0

def convert_graph_to_line():
    bubble = []
    nx, ny = n//2, n//2
    # 좌3 하2 우4 상1
    i = 3
    count = 1
    k = 1
    while True:
        nx, ny = nx+dx[i], ny+dy[i]
        bubble.append(graph[nx][ny])
        count -= 1
        if nx == 0 and ny == 0:
            break
        # 횟수 소진 -> 방향 전환,
        if count == 0:
            if i == 3:  # 좌 -> 하
                i = 2
                count = k
            elif i == 2:  # 하 -> 우
                i = 4
                k += 1
                count = k
            elif i == 4:  # 우 -> 상
                i = 1
                count = k
            elif i == 1:  # 상 -> 좌
                i = 3
                k += 1
                count = k
    return bubble


def crash_empty():
    new_bubble = []
    for i in range(len(bubble_list)):
        if bubble_list[i] != 0:
            new_bubble.append(bubble_list[i])
    return new_bubble


def crash_4bubble():
    count = 0
    check = False
    global score
    for i in range(1, len(bubble_list)):
        if bubble_list[i] == bubble_list[i-1]:
            count += 1
        else:
            if count >= 4:
                score += count*bubble_list[i-1]
                for j in range(count):
                    bubble_list[i-j] = 0
                    check = True
            count = 1
    return check


def transform_bubble():
    new_bubble = []
    count = 1
    for i in range(len(bubble_list) - 1):
        num = bubble_list[i]
        if bubble_list[i] == bubble_list[i+1]:
            count += 1
        else:
            new_bubble.append(count)
            new_bubble.append(num)
            count = 1
    return new_bubble


def convert_line_to_graph():
    new_graph = [[0 for _ in range(n)] for _ in range(n)]
    nx, ny = n // 2, n // 2
    # 좌3 하2 우4 상1
    i = 3
    count = 1
    k = 1
    j = 0
    while True:
        nx, ny = nx + dx[i], ny + dy[i]
        new_graph[nx][ny] = int(bubble_list[j])
        count -= 1
        j += 1
        if nx == 0 and ny == 0 or len(bubble_list) <= j:
            break
        # 횟수 소진 -> 방향 전환,
        if count == 0:
            if i == 3:  # 좌 -> 하
                i = 2
                count = k
            elif i == 2:  # 하 -> 우
                i = 4
                k += 1
                count = k
            elif i == 4:  # 우 -> 상
                i = 1
                count = k
            elif i == 1:  # 상 -> 좌
                i = 3
                k += 1
                count = k

    return new_graph



n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))

# 방향, 거리
magic = []
for i in range(m):
    magic.append(list(map(int, input().split())))

bubble_list = []
score = 0

for i in range(m):
    blizard(magic[i][0], magic[i][1])
    bubble_list = convert_graph_to_line()
    bubble_list = crash_empty()
    # 터지는게 없을 때까지, 터뜨리고, 빈공간 채우고 반복
    while crash_4bubble():
        bubble_list = crash_empty()
    # 구슬 변환, 다시 터뜨리기
    bubble_list = transform_bubble()
    graph = convert_line_to_graph()
    while crash_4bubble():
        bubble_list = crash_empty()
    print(i + 1)
    for j in range(n):
        print(graph[j])
print(score)
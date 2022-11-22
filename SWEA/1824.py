import random
# 상 하 좌 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def simulate(x, y, dir, count):
    global memory
    command = graph[x][y]
    random_dir = False

    if count > 1000:
        print("#" + str(test_case), "NO")
        return False

    if command == '<': dir = 2
    elif command == '>': dir = 3
    elif command == '^': dir = 0
    elif command == 'v': dir = 1
    elif command == '_':
        if memory == 0:
            dir = 3
        else:
            dir = 2
    elif command == '|':
        if memory == 0:
            dir = 1
        else:
            dir = 0
    elif command == '.':
        pass
    elif command == '@':
        # 프로그램 정지
        print("#" + str(test_case), "YES")
        return True
    elif command == '+':
        if memory == 15:
            memory = 0
        else:
            memory += 1
    elif command == '-':
        if memory == 0:
            memory = 15
        else:
            memory -= 1
    elif command == '?':
        random_dir = True
    else: # 0-9
        memory = int(command)

    if random_dir:
        for i in range(4):
            dir = i
            nx, ny = x+dx[dir], y+dy[dir]
            if nx < 0:
                nx = r-1
            if ny < 0:
                ny = c-1
            nx, ny = nx % r, ny % c
            simulate(nx, ny, dir, count+1)
    else:
        nx, ny = x + dx[dir], y + dy[dir]
        if nx < 0:
            nx = r - 1
        if ny < 0:
            ny = c - 1
        nx, ny = nx % r, ny % c
        simulate(nx, ny, dir, count + 1)


T = int(input())

for test_case in range(1, T+1):
    r, c = map(int, input().split())

    graph = []
    for i in range(r):
        graph.append(list(input()))
    memory = 0
    check = False
    for i in range(r):
        for j in range(c):
            if graph[i][j] == '@':
                check = True
    if check == True:
        simulate(0, 0, 3, 1)
    else:
        print("#" + str(test_case), "NO")
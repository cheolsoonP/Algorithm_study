

def add_redboard(t, x):
    if t == 1:
        for i in range(1, 6):
            if i == 5 and redboard[x][i] == 0:
                redboard[x][5] = 1
                break
            if redboard[x][i] == 1:
                redboard[x][i-1] = 1
                break
    if t == 2:
        for i in range(2, 6):
            if i == 5 and redboard[x][i] == 0:
                redboard[x][i], redboard[x][i-1] = 1, 1
                break
            if redboard[x][i] == 1:
                redboard[x][i-1], redboard[x][i-2] = 1, 1
                break
    if t == 3:
        for i in range(1, 6):
            if i == 5 and redboard[x][i] == 0 and redboard[x+1][i] == 0:
                redboard[x][i], redboard[x+1][i] = 1, 1
                break
            if redboard[x][i] == 1 or redboard[x+1][i] == 1:
                redboard[x][i-1], redboard[x+1][i-1] = 1, 1
                break

def add_yellowboard(t, y):
    if t == 1:
        for i in range(1, 6):
            if i == 5 and yellowboard[i][y] == 0:
                yellowboard[i][y] = 1
                break
            if yellowboard[i][y] == 1:
                yellowboard[i-1][y] = 1
                break
    if t == 2:
        for i in range(1, 6):
            if i == 5 and yellowboard[i][y] == 0 and yellowboard[i][y+1] == 0:
                yellowboard[i][y], yellowboard[i][y+1] = 1, 1
                break
            if yellowboard[i][y] == 1 or yellowboard[i][y+1] == 1:
                yellowboard[i-1][y], yellowboard[i-1][y+1] = 1, 1
                break
    if t == 3:
        for i in range(2, 6):
            if i == 5 and yellowboard[i][y] == 0 and yellowboard[i-1][y]:
                yellowboard[i][y], yellowboard[i-1][y] = 1, 1
                break
            if yellowboard[i][y] == 1:
                yellowboard[i-1][y], yellowboard[i-2][y] = 1, 1
                break


def remove_redboard(index):
    for i in range(index, 0, -1):
        for j in range(0, 4):
            redboard[j][i] = redboard[j][i-1]
    for i in range(4):
        redboard[i][0] = 0


def remove_yellowboard(index):
    for i in range(index, 0, -1):
        for j in range(0, 4):
            yellowboard[i][j] = yellowboard[i-1][j]
    for i in range(4):
        yellowboard[0][i] = 0


def get_red_score():
    red_count = 0
    remove_list = []
    for i in range(2, 6):
        if [row[i] for row in redboard] == [1, 1, 1, 1]:
            remove_list.append(i)
            red_count += 1

    for i in remove_list:
        remove_redboard(i)

    return red_count

def get_yellow_score():
    yellow_count = 0
    remove_list = []
    for i in range(2, 6):
        if yellowboard[i][:] == [1, 1, 1, 1]:
            remove_list.append(i)
            yellow_count += 1
    for i in remove_list:
        remove_yellowboard(i)

    return yellow_count


def check_over_red():
    for i in range(2):
        for j in range(4):
            if i == 0 and redboard[j][i] == 1:
                # 2칸 땡기기 - [4][5]
                remove_redboard(5)
                remove_redboard(5)
                break
            if i == 1 and redboard[j][i] == 1:
                # 1칸 땡기기
                remove_redboard(5)
                break

def check_over_yellow():
    for i in range(2):
        for j in range(4):
            # 옅은 공간에 블록이 있다면
            if i == 0 and yellowboard[i][j] == 1:
                # 2칸 땡기기 - [4][5]
                remove_yellowboard(5)
                remove_yellowboard(5)
                break
            if i == 1 and yellowboard[i][j] == 1:
                # 1칸 땡기기
                remove_yellowboard(5)
                break


def check_remain():
    count = 0
    for i in range(6):
        for j in range(4):
            if redboard[j][i] == 1:
                count += 1
            if yellowboard[i][j] == 1:
                count += 1
    return count


redboard = [[0 for _ in range(6)] for _ in range(4)]
yellowboard = [[0 for _ in range(4)] for _ in range(6)]

k = int(input())
game = []
for i in range(k):
    game.append(list(map(int, input().split())))

red_score = 0
yellow_score = 0
for i in range(k):
    t, x, y = game[i]

    add_redboard(t, x)
    add_yellowboard(t, y)
    red_score += get_red_score()
    yellow_score += get_yellow_score()
    check_over_red()
    check_over_yellow()

# for j in range(4):
#     print(redboard[j])
# print("")
# for j in range(6):
#     print(yellowboard[j])
# print("")
print(red_score + yellow_score)
print(check_remain())
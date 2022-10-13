# 1-상 2-하 3-좌 4-우
dx = [0, -1, 1, 0, 0]
dy = [0, 0, 0, -1, 1]

T = int(input())

# 1. 방향으로 이동
def move_group(group):
    # 이동
    for i in range(len(group)):
        x, y, num, direction = group[i]
        nx = x + dx[direction]
        ny = y + dy[direction]

        group[i][0], group[i][1] = nx, ny
    return group


# 선 밟은 지 확인, 밟으면 반 죽이고, 방향 전환
def check_line(group):
    for i in range(len(group)):
        x, y, num, direction = group[i]
        # 경계 안쪽 확인
        if 0 < x < n-1 and 0 < y < n-1:
            continue
        group[i][2] = num // 2
        if direction % 2 == 0:  # 짝수 2->1 4->3
            group[i][3] -= 1
        else:  # 홀수 1->2 3->4
            group[i][3] += 1
    return group


# 동시에 여러 그룹이 만났을 때, 어느 그룹이 승자인지 확인, 이긴 팀 점수와 다시 또 중복되는 팀 확인
def check_meeting(group):
    for i in range(0, len(group)-1):
        x1, y1, num1 = group[i][0], group[i][1], group[i][2]
        win_num = num1
        total = num1
        win_index = i
        duplicate = []
        duplicate.append(i)
        check = False
        for j in range(i+1, len(group)):
            x2, y2, num2 = group[j][0], group[j][1], group[j][2]
            if x1 == x2 and y1 == y2:
                total += num2
                check = True
                duplicate.append(j)
                if num2 > win_num:
                    win_num = num2
                    win_index = j
        if check is True:
            for d in duplicate:
                group[d][2] = 0
            group[win_index][2] = total

    return group


def check_total(group):
    total = 0
    for i in range(len(group)):
        total += group[i][2]
    return total


# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # 초기 세팅
    n, m, k = map(int, input().split())

    group_list = []
    for i in range(k):
        group_list.append(list(map(int, input().split())))
        # 세로, 가로, 미생물 수, 방향

    # m 시간동안 진행
    for i in range(m):
        group_list = move_group(group_list)
        group_list = check_line(group_list)
        group_list = check_meeting(group_list)
    answer = "#"+str(test_case)
    print(answer, check_total(group_list))



from collections import deque
# X상우하좌
dx = [0, -1, 0, 1, 0]
dy = [0, 0, 1, 0, -1]

def setting_bc(bc):
    bc_list = [[] for _ in range(len(bc))]

    for i in range(len(bc)):
        x, y, c, p = bc[i]
        x, y = x-1, y-1
        visited = [[0 for _ in range(10)] for _ in range(10)]
        q = deque()
        q.append((x, y))
        visited[x][y] = 1
        bc_list[i].append(p)
        bc_list[i].append((x, y))
        k = 0
        while q:
            if k >= c:
                break
            x, y = q.popleft()
            for j in range(1, 5):
                nx, ny = x+dx[j], y+dy[j]
                if nx < 0 or nx >= 10 or ny < 0 or ny >= 10:
                    continue
                if visited[nx][ny] == 1:
                    continue
                visited[nx][ny] = 1
                q.append((nx,ny))
                bc_list[i].append((nx, ny))
            k += 1

    return bc_list

# 유저가 어떤 충전소를 이용할 수 있는지 확인
def check_user(x, y, bc_list):
    use_bc = []
    for i in range(len(bc_list)):
        if (x, y) in bc_list[i][1:]:
            use_bc.append(i)

    return use_bc


def charge_user(x1, y1, x2, y2, bc_list):
    charge = 0

    # 유저가 사용 가능한 BC 확인
    a_use = check_user(x1, y1, bc_list)
    b_use = check_user(x2, y2, bc_list)


    # 유저가 사용 중인 BC가 없을 경우
    if len(a_use) <= 0 and len(b_use) <= 0:
        return 0
    # b유저만 사용 중일 경우
    if len(a_use) <= 0:
        return bc_list[b_use[0]][0]
    # a 유저만 사용 중일 경우
    if len(b_use) <= 0:
        return bc_list[a_use[0]][0]
    # 둘이 같은 bc를 사용 중일 경우
    if a_use[0] == b_use[0]:
        # 대체할 BC가 없는 경우
        if len(a_use) == 1 and len(b_use) == 1:
            return bc_list[a_use[0]][0]
        # 대체할 BC가 둘 다 있는 경우
        if len(a_use) >= 2 and len(b_use) >= 2:
            if a_use[1] >= b_use[1]:
                return bc_list[a_use[1]][0] + bc_list[b_use[0]][0]
            else:
                return bc_list[a_use[0]][0] + bc_list[b_use[1]][0]
        # 대체할 BC가 a만 있는 경우
        if len(a_use) > len(b_use):
            return bc_list[a_use[1]][0] + bc_list[b_use[0]][0]
        # 대체할 BC가 b만 있는 경우
        else:
            return bc_list[a_use[0]][0] + bc_list[b_use[1]][0]
    # 둘이 다른 BC를 사용할 경우
    return bc_list[a_use[0]][0] + bc_list[b_use[0]][0]


def move_user(x1, y1, x2, y2, dir_a, dir_b):
    return x1+dx[dir_a], y1+dy[dir_a], x2+dx[dir_b], y2+dy[dir_b]


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    m, a = map(int, input().split())

    dir_a = list(map(int, input().split()))
    dir_b = list(map(int, input().split()))

    bc = []
    for i in range(a):
        bc.append(list(map(int, input().split())))

    bc_list = setting_bc(bc)
    bc_list = sorted(bc_list, reverse=True)
    # 초기 유저 좌표에서 충전
    x1, y1, x2, y2 = 0, 0, 9, 9
    charge = charge_user(x1, y1, x2, y2, bc_list)
    # 유저 이동 -> 유저 겹치는지 확인 -> 충전
    for i in range(m):

        x1, y1, x2, y2 = move_user(x1, y1, x2, y2, dir_a[i], dir_b[i])
        charge += charge_user(x1, y1, x2, y2, bc_list)
        print(charge)

    print("#"+str(test_case), charge)
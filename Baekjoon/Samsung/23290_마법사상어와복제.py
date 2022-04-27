"""
파이어볼 토네이도 파이어스톰 물복사버그 비바라기 블리자드
4 x 4 격자 내에 M마리 물고기 방향을 가짐 상하좌우 대각 8가지
상어도 격자앙ㄴ에 있다.
물고기는 같은칸에 둘 이상 있을 수 있다.
상어도 물고기랑 같이 있을 수 있다.

상어는 마법 연습 한번은 순차적으로 한다.
1. 모든 물고기에게 복제 마법, 5번에서 물고기가 복제되어 칸에 나타난다
2. 물고기가 한칸이동 자신이 가진 방향으로,
단, 상어칸, 물고기 냄새가 있는칸, 격자 범위 밖으로는
이동할 수 없다.
이동할 수 있는 칸을 향할때까지 45도씩 반시계로 화전한다.
이동할 수 있는 칸이 없으면 이동을 하지 않는다.
3. 상어가 연속해서 3칸 이동한다.
상어는 상하좌우로 이동할 수 있다 연속해서 이동하는 칸 중에 격자를 벗어나면 이동할 수 없는 곳이다
상어가 이동한 곳에 있는 모든 물고기는 사라지고 물고기 냄새를 남긴다
이동 동선에 물고기가 가장 많은 곳으로 이동한다.
여러가지일 경우(수가 동일)할 경우 사전 순으로 가장 앞서는 방법을 이용한다

4. 두전 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
5. 1에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖는다

격자 내 물고기 위치,방향, 상어위치, 연습 횟수S가 주어진다.
격자의 물고기 수를 구해라

첫째 줄에 물고기의 수 M, 상어가 마법을 연습한 횟수 S가 주어진다.

둘째 줄부터 M개의 줄에는 물고기의 정보 fx, fy, d가 주어진다.
(fx, fy)는 물고기의 위치를 의미하고, d는 방향을 의미한다.
방향은 1~8
순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다.

마지막 줄에는 sx, sy가 주어지며, 상어가 (sx, sy)에 있음을 의미한다.

격자 위에 있는 물고기의 수가 항상 1,000,000 이하인 입력만 주어진다.
"""

from copy import deepcopy

m, s = map(int, input().split())

# 격자는 4x4로 고정이다.
fish = [[[] * 4 for _ in range(4)] for _ in range(4)]
for i in range(m):
    # fish[][0] = r / fish[][1] = c / fish[][2] = direction
    x, y, dir = map(int, input().split())
    fish[x][y].append(dir-1)

print(fish)
# shark_x, shark_y = map(int, input().split())
#
# fish_smell = [[] * 4 for _ in range(4)]
#
# # 마법 연습 1사이클 -
# # 복제,
# # 물고기 이동,
# # 상어 이동,
# # 물고기 냄새 삭제,
# # 복제 완료
#
# # 1. 복제
# def fish_copy(fish):
#     temp = deepcopy(fish)
#
#     return temp
#
#
# # 2-1. 방향 회전 (반시계로)
# def rotate_direction(direct):
#     if direct == 0:
#         return 7
#     else:
#         return direct - 1
#
#
# # 2. 물고기 이동
# def move_fish(fish):
#     # 0 ~ 7
#     # ←, ↖, ↑, ↗, →, ↘, ↓, ↙
#     dx = [0, -1, -1, -1, 0, 1, 1, 1]  # row
#     dy = [-1, -1, 0, 1, 1, 1, 0, -1]  # column
#
#     # 물고기들이 이동을 한다. 겹칠 수 있다.
#     for i in range(len(fish)):
#         direct = fish[i][2]  # 초기 방향
#         for _ in range(8):
#             nx = fish[i][0] + dx[direct]
#             ny = fish[i][1] + dy[direct]
#             # 범위를 초과,
#             # 상어가 있거나
#             # 물고기 냄새가 있거나
#             # 반시계로 회전
#             if 0 < nx <= 4 and 0 < ny <= 4:
#                 if nx != shark_x and ny != shark_y:
#                     if fish_smell[nx][ny] > 0:
#                         # 물고기 이동
#                         fish[i][0] = nx
#                         fish[i][1] = ny
#                         fish[i][2] = direct
#             else:
#                 direct = rotate_direction(direct)
#
# # 상어가 이동할 방향 선정
# # 먹을 물고기가 많은 곳, 범위를 벗어나지 않는 곳
# # 물고기 카운트, 방향 정하기
# def dfs_get_position(fish, shark_x, shark_y, count, depth):
#     if depth == 3:
#         return shark_x, shark_y
#     # 상 하 좌 우
#     dx = [-1, 1, 0, 0]  # row
#     dy = [0, 0, -1, 1]  # column
#
#     for i in range(4):
#         nx, ny = shark_x + dx[i], shark_y + dy[i]
#         if 0 < nx < 5 and 0 < ny < 5:
#             for f in range(len(fish)):
#                 if fish[f][0] == nx and fish[f][1] == ny:
#                     count += 1
#
#
#
#
#
# # 상어 이동
# def move_shark(shark_x, shark_y):
#     # 상 하 좌 우 3칸씩 이동 가능
#     find
#

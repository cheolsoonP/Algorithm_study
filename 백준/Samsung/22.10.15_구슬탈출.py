# 왼 위 오 아


n, m = map(int, input().split())

board = []

for i in range(n):
    board.append(list(input()))

print(board)

for i in range(1, 11):
        left_board()
        right_board()
        up_board()
        down_board()

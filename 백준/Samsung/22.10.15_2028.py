def up_board(board):
    new_board = [[0 for _ in range(n)] for _ in range(n)]
    new_board[0] = board[0]
    for i in range(n):
        count = 0
        for j in range(1, n):
            new_board[j-count][i] = board[j][i]
            if new_board[j-count][i] == new_board[j-1-count][i]:
                new_board[j-count][i] = 0
                new_board[j-1-count][i] *= 2
                count += 1
    return new_board


def rotate_90(board):
    new_board = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            new_board[j][n-1-i] = board[i][j]
    return new_board

def get_max(board):
    score = 0
    for i in range(n):
        for j in range(n):
            if board[i][j] > score:
                score = board[i][j]
    return score

def dfs(cur, count):
    print("cur")
    for i in range(n):
        print(cur[i])
    global answer
    if count == 5:
        cur_score = get_max(cur)
        if answer < cur_score:
            answer = cur_score
        return

    for dir in range(4):
        new_board = up_board(cur)
        dfs(new_board, count+1)
        cur = rotate_90(cur)

n = int(input())

board = []
for i in range(n):
    board.append(list(map(int, input().split())))

answer = 0
dfs(board, 1)

print(answer)


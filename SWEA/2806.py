from collections import deque

RESULT = 0
T = int(input())

def dfs(row, count):
    global RESULT

    if count == n:
        RESULT += 1
        return

    for i in range(n):
        check = True
        q.append(i)
        for j in range(0, row):
            if q[j] == q[row]:
                check = False
            elif abs(q[j]-q[row]) == abs(j - row):
                check = False
        if check:
            dfs(row+1, count+1)
        q.pop()

for test_case in range(1, T+1):
    n = int(input())
    q = deque()

    RESULT = 0
    dfs(0, 0)
    print("#"+str(test_case), RESULT)
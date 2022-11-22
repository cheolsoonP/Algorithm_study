
T = int(input())

for test_case in range(1, T+1):
    # 15번 중에 8번 이상 이기면 면제
    result = list(input())
    count = 0
    for i in range(len(result)):
        if result[i] == 'o':
            count += 1

    # 남은 경기수 + 현재 이긴 경기수 >= 8
    if 15 - len(result) + count >= 8:
        print("#"+str(test_case), "YES")
    else:
        print("#"+str(test_case), "NO")

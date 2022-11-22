
T = int(input())

for test_case in range(1, T+1):
    n = list(map(int, input()))
    # 0이 아닌 가장 작은 수 -> 맨 앞으로 - 최솟값
    # 가장 큰 수 -> 맨 앞으로 - 최댓값

    min_n = [-1, 1e9]
    max_n = [-1, -1e9]
    for i in range(len(n)):
        if n[i] > 0 and min_n[1] > n[i]:
            min_n = [i, n[i]]
        if n[i] > max_n[1]:
            max_n = [i, n[i]]

    # 가장 작은 수 == 첫번째 숫자
    if min_n[0] == 0:

    n[0], n[min_n[0]] = n[min_n[0]], n[0]
    small_num = n[:]
    n[0], n[min_n[0]] = n[min_n[0]], n[0]

    n[0], n[max_n[0]] = n[max_n[0]], n[0]
    big_num = n[:]
    n[0], n[max_n[0]] = n[max_n[0]], n[0]

    s_num = ""
    b_num = ""
    for i in range(len(small_num)):
        s_num += str(small_num[i])
    for i in range(len(big_num)):
        b_num += str(big_num[i])

    print("#"+str(test_case), int(s_num), int(b_num))

T = int(input())

for _ in range(T):
    temp_list = [0 for _ in range(101)]
    max_num = 0
    max_idx = 0
    n = int(input())
    num_list = list(map(int, input().split()))
    for i in range(1000):
        temp_list[num_list[i]] += 1

    for j in range(101):
        if temp_list[j] >= max_num:
            max_num = temp_list[j]
            max_idx = j

    print("#{}".format(n), max_idx)
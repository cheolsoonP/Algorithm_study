T = int(input())

for test_case in range(1, T+1):
    nums = sorted(list(map(int, input().split())))

    answer = 0
    for i in range(1, len(nums)-1):
        answer += nums[i]

    answer = round(answer / (len(nums) - 2))

    print("#"+str(test_case), answer)



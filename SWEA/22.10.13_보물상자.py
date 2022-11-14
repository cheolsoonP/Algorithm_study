
def check_line_num(nums, count):
    num_group = []
    for i in range(0, len(nums), count):
        num = ""
        for s in nums[i:i+count]:
            num += s
        num_group.append(num)

    return num_group


def rotation_nums(nums):
    new_nums = ["" for _ in range(len(nums))]
    new_nums[0] = nums[len(nums)-1]
    new_nums[1:] = nums[:len(nums)-1]

    return new_nums


def sort_nums(nums, count, k):
    convert_nums = set()
    # 모든 숫자 16 to dec 로 변경
    for hex_num in nums:
        convert_nums.add(int(hex_num, 16))

    convert_nums = list(convert_nums)
    convert_nums = sorted(convert_nums,key = lambda x:x[1], reverse=True)

    return convert_nums[k-1]



T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n, k = map(int, input().split())
    init_nums = list(map(str, input()))

    count = n // 4
    nums = init_nums
    nums_list = set()

    while True:
        # 숫자 업데이트
        nums_list.update(check_line_num(nums, count))
        # 숫자 회전
        nums = rotation_nums(nums)
        if nums == init_nums:
            break
    print("#"+str(test_case), sort_nums(list(nums_list), count, k))
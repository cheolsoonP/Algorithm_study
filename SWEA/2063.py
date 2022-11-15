n = int(input())

nums = list(map(int, input().split()))
nums = sorted(nums)

print(nums[len(nums)//2])
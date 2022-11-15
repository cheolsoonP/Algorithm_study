"""
반올림 round()
올림 ceil()
내림 floor()
"""

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    nums = list(map(int, input().split()))
    result = round(sum(nums) / len(nums))

    print("#"+str(test_case), result)
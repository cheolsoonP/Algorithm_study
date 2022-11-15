"""
3 6 9 들어가면 박수
3, 6 , 9 갯수 만큼 - 출력
나머지는 숫자 출력
"""

def check_clab(num):
    clab = ['3', '6', '9']
    nums = list(num)
    count = 0
    for i in range(len(nums)):
        if nums[i] in clab:
            count += 1

    return count


n = int(input())

for i in range(1, n+1):
    num = str(i)
    count = check_clab(num)
    if count > 0:
        for j in range(count):
            print('-', end="")
        print(end=" ")
    else:
        print(num, end=" ")

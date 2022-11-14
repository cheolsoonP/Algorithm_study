"""
22.11.11 싸피준비

"""

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())

    winner = 'Alice'

    if n % 2 == 1:
        winner = 'Bob'

    print("#" + str(test_case), winner)

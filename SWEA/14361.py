"""
N = 12345
재배열 -> 2N or 3N ... 배수의 숫자를 만들 수 있나?
재배열 -> N으로 나누었을때 나머지가 0 -> 숫자가 있나?

5! -> 경우의 수

or
N을 배수 -> 최대 10 -> 구성된 숫자 같으면 ->

"""

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    answer = "impossible"
    x = int(input())
    x_list = sorted(list(str(x)))


    for i in range(2, 10):
        nx = x * i
        nx_list = sorted(list(str(nx)))
        if x_list == nx_list:
            answer = "possible"
            break
    print("#" + str(test_case), answer)

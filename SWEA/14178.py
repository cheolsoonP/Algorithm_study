"""
1~n 꽃있다.
x 좌표에 분무기 -> x-d x+d 물 줄 수 있다.

n, d 모든 꽃이 한 개 이상 분무기에서 물을 받을 수 있게 하는 최소 분무기 개수

10 , d = 3
4번 위치 -> 123 4 567 , 1+d*2 -> 분무기 1개
"""

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n, d = map(int,input().split())
    cover = d * 2 + 1
    if n % cover > 0:
        print("#"+str(test_case), n // cover + 1)
    else:
        print("#"+str(test_case), n // cover)

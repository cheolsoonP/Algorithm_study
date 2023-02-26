"""
[BOJ] 16637_괄호 추가하기
23.02.26

길이 N 수식
수식 0보다 크거나 같고 9보다 작은 정수
연산자 + - x
우선순위는 모두 동일, 왼쪽부터 순서대로 계산
괄호를 추가하면 괄호부터 계산한다.
괄호안에 연산자는 하나만 가능

중첩괄호 올바른 식 아니다

수식 -> 괄호를 적절히 추가 -> 만들 수 있는 식의 결과의 최댓값을 구해라
괄호 개수 제한 없다.
추가하지 않아도 된다.
0. 최대 괄호 개수 구하기
1. 괄호 들어갈 수 있는 조합
2. 해당 위치에 괄호 넣고 계산하기

수식 길이 1 ~ 19
"""

n = int(input())
arr = list(input())
new_arr = []
ans = 0
max_br = n//2
br_pos = []
print(ord('0'))
print(arr)

def dfs(br_cnt):
    if br_cnt == 0:
        # 계산


    for i in range(n):
        new_arr.append('(')
        new_arr.append(')')


def braket():
    global new_arr
    # 괄호 0개 있을 때 ~ 괄호 N개 있을 때
    for i in range(0, max_br+1):
        new_arr = []
        dfs(i)


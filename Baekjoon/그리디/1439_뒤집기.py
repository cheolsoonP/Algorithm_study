# 다솜이는 0과 1로만 이루어진 문자열 S를 가지고 있다.
# 다솜이는 이 문자열 S에 있는 모든 숫자를 전부 같게 만들려고 한다.
# 다솜이가 할 수 있는 행동은 S에서 연속된 하나 이상의 숫자를 잡고 모두 뒤집는 것이다.
# 뒤집는 것은 1을 0으로, 0을 1로 바꾸는 것을 의미한다.
#
# 예를 들어 S=0001100 일 때,
#
# 전체를 뒤집으면 1110011이 된다.
# 4번째 문자부터 5번째 문자까지 뒤집으면 1111111이 되어서 2번 만에 모두 같은 숫자로 만들 수 있다.
# 하지만, 처음부터 4번째 문자부터 5번째 문자까지 문자를 뒤집으면
# 한 번에 0000000이 되어서 1번 만에 모두 같은 숫자로 만들 수 있다.
#
# 문자열 S가 주어졌을 때, 다솜이가 해야하는 행동의 최소 횟수를 출력하시오.

import sys

s = []
s = sys.stdin.readline()
new_s = []

# 처음문자가 0이면 0의 시작과 끝을 찾는다.(0~2) 0000
# 바뀌는 지점에서 그 시작과 끝을 찾는다.(3~5) 1111
# 또 바뀌는 지점에서 시작과 끝을 찾는다.(6~10) 0000
# 또 바뀌는 지점에서 시작과 끝을 찾는다.(11~15) 11111
# count가
# 1 : 0
# 2 : 1
# 3 : 1
# 4 : 2
# 5 : 2
# 6 : 3
# count가 짝수면 /2
# count가 홀수면 -1 /2
# 바뀌는 지점의 count가 홀수면 가운데꺼를 바꿔준다.
# 바뀌는 지점의 count가 짝수면 (짝수번째에 있는 거를 반대로 바꿔준다.)


count = 0
result = 0

for i in range(1, len(s)):
    if s[i-1] != s[i]:
        count += 1

if count == 1:
    result = 0
elif count % 2 == 1:  # 홀수
    result = int((count-1) / 2)
else:           # 짝수
    result = int(count / 2)

print(result)



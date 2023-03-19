# N 수열 의 합을 구한다 그냥 더하는게 아니라 수열의 두 수를 묶으려 한다.
# 위치에 상관없이 묶을 수 있다.
# 같은 위치를 묶는 것은 불가능
# 묶은 수는 서로 곱한 후에 더한다.
# 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때,
# 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다.
# 하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어
# 최대가 된다.
# 5 4 3 2 1 0
# () 3 2 1 0
# 수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.
#
# 수열이 주어졌을 때,
# 수열의 각 수를 적절히 묶었을 때,
# 그 합이 최대가 되게 하는 프로그램을 작성하시오.

# 수열을 정렬한다. 아예 안묶고 더한 것 < 맨 뒤에 두개를 묶어서 곱하여 더한 것

# 수 묶기 규칙을 찾아야 한다.

# 0, 음수 = 곱셈
# 0, 양수 = 덧셈

# 양수 양수 = 곱셈
# 양수 음수 = 덧셈
# 음수 음수 = 곱셈

# 1, 양수 = 덧셈
# 1, 음수 = 덧셈


import sys

n = int(sys.stdin.readline())

positive = []
negative = []
one = []

for i in range(0, n):
    temp = int(sys.stdin.readline())

    if temp > 1:
        positive.append(temp)
    elif temp <= 0:
        negative.append(temp)
    else:
        one.append(temp)

positive.sort(reverse=True)  # 3 2 1 내림차순 정렬
negative.sort()  # -4 -3 -2 ...

result = 0

# 양수 리스트가 짝수개이면 2개씩 묶어서 곱한 후 더한다.
if len(positive) % 2 == 0:
    for i in range(0, len(positive), 2):
        result += positive[i] * positive[i+1]
else:
    for i in range(0, len(positive)-1, 2):
        result += positive[i] * positive[i + 1]
    result += positive[len(positive)-1]

# 음수 리스트가 짝수개이면 2개씩 묶어서 곱한 후 더한다.
# 음수 리스트가 홀수 개이면 2개씩 묶고 남은 하나를 더한다.
if len(negative) % 2 == 0:
    for i in range(0, len(negative), 2):
        result += negative[i] * negative[i+1]
else:
    for i in range(0, len(negative)-1, 2):
        result += negative[i] * negative[i + 1]
    result += negative[len(negative)-1]

result += sum(one)

print(result)
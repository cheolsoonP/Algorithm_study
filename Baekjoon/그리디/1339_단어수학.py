# 예를 들어, GCF + ACDEB를 계산한다고 할 때,
# A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면,
# 두 수의 합은 99437이 되어서 최대가 될 것이다.

# 1704 ~

# 첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다.
# 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다.
# 단어는 알파벳 대문자로만 이루어져있다.
# 모든 단어에 포함되어 있는 알파벳은 최대 10개이고,
# 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.

import sys

dic = {'A':0, 'B':0, 'C':0, 'D':0, 'E':0, 'F':0, 'G':0, 'H':0, 'I':0,
       'J':0, 'K':0, 'L':0, 'M':0, 'N':0, 'O':0, 'P':0, 'Q':0,
       'R':0, 'S':0, 'T':0, 'U':0, 'V':0, 'W':0, 'X':0, 'Y':0, 'Z':0}


n = int(input())
alphabet = []
pocket = []

usedAlphabet = []
total = 0

for i in range(0, n):
    pocket.append(input())

for alphabet in pocket:
    for i in range(len(alphabet)):  # ABC
        num = 10 ** (len(alphabet) - i - 1)  # 10^2
        dic[alphabet[i]] += num  # A : 10^2 / b:10^1 / C:10

for value in dic.values():
    if value > 0:
        usedAlphabet.append(value)


sortedList = sorted(usedAlphabet, reverse=True)

k = 9
for i in sortedList:
    total += i * k
    k -= 1

print(total)


# 길이가 제일 긴 것을 찾는다.
# 제일 긴 것에서 최고자리에 9, 8 ...
# 짧은 곳
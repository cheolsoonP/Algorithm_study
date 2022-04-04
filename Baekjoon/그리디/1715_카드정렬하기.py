# 정렬된 두 숫자 카드 묶음, 각 묶음의 카드수 각 ,A , B 두 묶음을 합쳐서 하나로 만드는데에는
# A+B번의 비교를해야한다.
# 20장의(A)카드와 30장의(B)카드를 합치려면 50번 비교 해야한다.
# N개의 카드 뭈음이 주어질째 최소 몇번의 비교 최소!! 이게 그리디를 알려준다.
# 40 100 40 30 20
# 20 30 40 40 100
# total 50(t1) + 80(t2) +

# (20 30) (40 40) (50 80) (130 100) 50 80 130 230 260
# (20 30) (50 40) (90 40) (130 100) 50 90 130 230 140 500
# 50 80 130 230 :490


# 우선순위 큐 문제

import sys
import heapq # 우선순위 큐

n = int(sys.stdin.readline())
cardList = []
for i in range(n):
    cardList.append(int(sys.stdin.readline()))
heapq.heapify(cardList)

total = 0
# (10 20) 30) 40)

while len(cardList) != 1:
    num1 = heapq.heappop(cardList)
    num2 = heapq.heappop(cardList)

    sum = num1 + num2
    total += sum
    heapq.heappush(cardList, sum)

print(total)
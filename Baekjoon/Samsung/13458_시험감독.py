# 문제
# 총 N개의 시험장이 있고, 각각의 시험장마다 응시자들이 있다. i번 시험장에 있는 응시자의 수는 Ai명이다.

# 감독관은 총감독관과 부감독관으로 두 종류가 있다. 총감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 B명이고, 부감독관은 한 시험장에서 감시할 수 있는 응시자의 수가 C명이다.
#
# 각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.
#
# 각 시험장마다 응시생들을 모두 감시해야 한다. 이때, 필요한 감독관 수의 최솟값을 구하는 프로그램을 작성하시오.
#
# 입력
# 첫째 줄에 시험장의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
#
# 둘째 줄에는 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)가 주어진다.
#
# 셋째 줄에는 B와 C가 주어진다. (1 ≤ B, C ≤ 1,000,000)
#
# 출력
# 각 시험장마다 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수를 출력한다.


# N개 시험장 시험장마다 응시장 i번 시험장에 응시자 - Ai명
# 감독관 두 종류 총감독관, 부감독관 총 감독관 - B명, 부감독관 - C명 한 시험장 내 응시자수
# 각 시험장에 총 감독관은 1명, 부감독관은 여러명
# 각 시험장마다 응시생 모두 감시해야 함 필요한 감독관의 최솟값을 구하는 프로그램
# 첫째줄 - 시험장 개수 N // 둘째줄 각 시험장의 응시자수 Ai 주어짐.
# 셋째줄 - B(총감독이 담당할수있는 인원)와 C(부감독이 담당가능한 인원)
# Ai <= C / Ai <= B / B, C < Ai
# -> 각 시험장에 필요한 감독 수를 계산, 각 시험장마다 총감독, 부감독이 있음, 각 시험장별 감당 가능한 인원 B + xC

# 04.14
# def findMinManager(studentsList, firstManager, secondManager):
#     count = 0
#
#     for students in studentsList:
#         if students <= firstManager:
#             count += 1
#         elif students <= firstManager + secondManager:
#             count += 2
#         else:
#             # 총감독 1명
#             count += 1
#             if (students - firstManager) % secondManager:
#                 # 부감독 n명
#                 count += (((students - firstManager) // secondManager) + 1)
#             else:
#                 count += ((students - firstManager) // secondManager)
#
#     return count
#
# N = int(input())
# studentsList = list(map(int, input().split()))
# firstManager, secondManager = map(int, input().split())
#
# print(findMinManager(studentsList, firstManager, secondManager))

def findMinManager():
    count = N
    for students in studentsList:
        students -= boss
        if students > 0:
            if students % sub:
                count += students // sub + 1
            else:
                count += students // sub
    return count



N = int(input())
studentsList = list(map(int, input().split()))
boss, sub = map(int, input().split())

print(findMinManager())

# 예제 입력 1
# 1
# 1
# 1 1
# 예제 출력 1
# 1
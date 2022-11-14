
def check_monthly_cost(plan, daily, month):
    monthly_cost = []
    for i in range(len(plan)):
        cost = plan[i] * daily
        if cost > month:
            cost = month
        monthly_cost.append(cost)
    print("monthly_cost ", monthly_cost)
    return monthly_cost


def check_three_month(monthly_cost, three_month):
    three_monthly_cost = []
    for i in range(len(monthly_cost)):
        cost = 0
        if i == 11:
            cost = monthly_cost[i]
        elif i == 10:
            cost = monthly_cost[i] + monthly_cost[i + 1]
        else:
            cost = monthly_cost[i]+monthly_cost[i+1]+monthly_cost[i+2]
        if cost > three_month:
            cost = three_month
        three_monthly_cost.append(cost)

    print("three_monthly_cost ", three_monthly_cost)
    return three_monthly_cost

def find_least_cost(monthly_cost, three_monthly_cost, three_month, year):
    total = 0
    for i in range(len(monthly_cost)):
        # 3달 요금이 더 싼 경우
        cost = 0
        if i == 10: # 11월
            cost = monthly_cost[i] + monthly_cost[i+1]
        elif i == 11:
            cost = monthly_cost[i]
        else:
            cost = monthly_cost[i] + monthly_cost[i+1] + monthly_cost[i+2]
            
        if cost < three_month:


    for i in range(len(monthly_cost)):
        total += monthly_cost[i]
    if total > year:
        total = year

    return total

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    daily, month, three_month, year = map(int, input().split())
    plan = list(map(int, input().split()))  # 1월 ~ 12월

    monthly_cost = check_monthly_cost(plan, daily, month)
    three_monthly_cost = check_three_month(monthly_cost, three_month)
    least_cost = find_least_cost(monthly_cost, three_monthly_cost, three_month, year)

    print(least_cost)

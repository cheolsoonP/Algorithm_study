
n = int(input())

graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))


# 팀의 조합 구하기 - 모든 팀 구성
def dfs(level, start):
    if level == (n // 2):
        combi_list.append(result[:])
        return

    for i in range(start, n):
        result[level] = i
        dfs(level+1, i+1)


# 팀 리스트
combi_list = []
result = [0] * (n // 2)
dfs(0, 0)


# 팀 내 코스트 구하기
def get_cost(team):
    # 팀 내 멤버쌍 구하기
    member_pair = []
    team_cost = 0

    for i in range(len(team)):
        for j in range(len(team)):
            if team[i] != team[j]:
                member_pair.append([team[i], team[j]])

    # 코스트 합 구하기
    for i, j in member_pair:
        team_cost += graph[i][j]

    return team_cost


# 각 팀별 코스트 계산
team_costs = []
for team in combi_list:
    team_costs.append(get_cost(team))

# 대응 되는 팀의 비용차 계산
min_diff = 1e9
for i in range(0, len(team_costs)//2):
    min_diff = min(min_diff, abs(team_costs[i] - team_costs[len(team_costs)-1-i]))

print(min_diff)
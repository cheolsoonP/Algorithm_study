
def make_team(c, team):
    if len(team) == n//2:
        teams.append(team[:])
        return
    if c > n:
        return
    team.append(c)
    make_team(c+1, team)
    team.pop()
    make_team(c+1, team)

def get_per_team_score(team):
    score = 0
    for i in range(len(team)):
        for j in range(len(team)):
            score += graph[team[i]-1][team[j]-1]
    return score

def get_team_score(teams):
    score = []
    # 각 팀당 점수 계산
    for i in range(len(teams)):
        score.append(get_per_team_score(teams[i]))
    return score

n = int(input())
num_member = n//2
# 1~n까지 사람 있음.
graph = []
for i in range(n):
    graph.append(list(map(int, input().split())))


teams = []
make_team(1, [])
score_list = []
score_list = get_team_score(teams)

start_team = score_list[:int(len(teams)/2)]
link_team = score_list[int(len(teams)/2):]
answer = 1e9
for i in range(len(start_team)):
    dis = abs(start_team[i] - link_team[len(start_team)-1-i])
    if answer > dis:
        answer = dis

print(answer)
"""

‘#'(벽), ‘.’ (빈 공간), ‘S’ (시작점), ‘E' (도착점)
그리고 ‘1'이상 '9’이하의 숫자로 이루어진
N×N 격자 정보
벽은 해당 위치로 갈 수 없음을 의미하며,
숫자의 경우 해당 위치에 동전이 놓여져 있음을 의미
동전은 위치에 가면 얻음
각 위치에 하나씩 놓임,
시작점에서 풀발해서 적절히 이동해서 최소 3개 동전을 수집해서
도착점으로 가야한다.

동전에 숫자는 증가하는 순서대로 이전 동전보다 숫자가 커야한다.
위치를 지나가도 동전을 수집하지 않아도 된다.
같은 위치 2번 이상 지나가는 것도 상관없다.

S -> E로 가야한다.
벽은 못지나 간다.
동전 3개를 주워야한다. 작은 번호부터 주울 수 있다.
최소 이동 횟수를 구해라.
1 > 2 > 3

격자 크기 N 주어진다.
N개 줄 격자 정보주어진다.
이동이 불가능하다면 -1 출력

"""

n = int(input())
graph = []
for i in range(n):
    graph.append(list(input()))

print(graph)
start_x, start_y = 0, 0
end_x, end_y = 0, 0

# 상 하 좌 우
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

for i in range(n):
    for j in range(n):
        if graph[i][j] == 'S':
            start_x = i
            start_y = j

result = []
coin_set = set('123456789')
graph[start_x][start_y] = '.'

def dfs(temp, x, y, cost, coin_count, prev_coin):
    # 도착 지점, 동전 3개 이상 - 비용 저장
    if graph[x][y] == 'E' and coin_count >= 3:
        result.append(cost)

    for i in range(4):
        # 벽이면 다른 방향으로 가기
        # 동전이면 앞전 동전보다 높은거면 줍기
        # 동전이면 일단 패스하기,
        # 앞전 동전의 수보다 낮으면 패스
        if 0 <= x+dx[i] < n and 0 <= y+dy[i] < n and temp[x+dx[i]][y+dy[i]] != '#':

            # 동전이 있는 곳에서
            print(temp[x][y])
            if temp[x][y] in coin_set:
                # 이전 동전보다 클 경우 동전을 줍는다.
                if int(temp[x][y]) > prev_coin:
                    coin_count += 1
                    temp[x][y] = '.'
                    prev_coin = int(temp[x][y])
                    dfs(temp, x+dx[i], y+dy[i], cost+1, coin_count, prev_coin)
            if temp[x+dx[i]][y+dy[i]] == '.':
                dfs(temp, x+dx[i], y+dy[i], cost+1, coin_count, prev_coin)


a = dfs(graph, start_x, start_y, 0, 0, 0)

print(result)












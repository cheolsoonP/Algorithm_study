
"""

"""

def dfs(member, count):
    isVisited[member] = True
    for i in range(len(couple_list)):
        if couple_list[i][0] == member and isVisited[couple_list[i][1]] != True:
            dfs(couple_list[i][1], count+1)
    return count


n, k = map(int, input().split())

couple_list = []
for i in range(k):
    couple_list.append(list(map(int, input().split())))

isVisited = [0 for _ in range(n+1)]
max_count = 0
groups = 0
for i in range(1, n+1):
    if isVisited[i] == True:
        continue
    max_count = max(max_count, dfs(i, 1))
    groups += 1






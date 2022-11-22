
def search_palindrom():
    count = 0
    for i in range(8):
        for j in range(0, 8-n+1):
            letter = graph[i][j:j+n]
            if letter == letter[::-1]:
                count += 1
    return count


def rotation_90():
    new_graph = [[0 for _ in range(8)] for _ in range(8)]
    for i in range(8):
        for j in range(8):
            new_graph[8-1-j][i] = graph[i][j]
    return new_graph

T = 10

for test_case in range(1, T+1):
    n = int(input())
    graph = []
    for i in range(8):
        graph.append(list(input()))
    answer = 0

    answer += search_palindrom()
    graph = rotation_90()
    answer += search_palindrom()

    print("#"+str(test_case), answer)
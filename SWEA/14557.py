
T = int(input())

for test_case in range(1, T+1):
    cards = list(map(int, input()))
    answer = 'yes'
    while True:
        check = False
        for i in range(len(cards)):
            if cards[i] == 1:
                cards[i] = -1
                check = True
                if i-1 >= 0:
                    if cards[i-1] == 0:
                        cards[i-1] = 1
                    elif cards[i-1] == 1:
                        cards[i-1] = 0
                if i+1 < len(cards):
                    if cards[i+1] == 0:
                        cards[i+1] = 1
                    elif cards[i+1] == 1:
                        cards[i+1] = 0
        if check == False:
            break

    for card in cards:
        if card >= 0:
            answer = 'no'

    print("#"+str(test_case), answer)
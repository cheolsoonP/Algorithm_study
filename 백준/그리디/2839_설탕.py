# 설탕 배달

n = int(input())

bag = 0
while n >= 0:
    if n % 5 == 0:  # 5로 나눈 나머지가 0인 경우
        bag += n // 5  # 5로 나눈 몫 봉지 수 추가
        print(bag)
        break
    n -= 3  # 설탕이 5의 배수가 될 때까지 3개씩 빼줌.
    bag += 1  # 봉지 수 추가

else:
    print(-1)  # while문이 거짓이 되면 -1 출력

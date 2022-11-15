"""
1. 연속 N일 동안 매매를 예측 알고있다
2. 감시망에 걸리지 않으려면 하루 최대 1개만 구입
3. 판매는 얼마든지 가능

가격 제일 낮은 곳에서 사서 최대에서 판매
구메 > 판매

가장 비용이 비싼 곳 찾기 -> 맨 뒤에서

구매 - 제일 낮은 곳에서 구매 -> 판매 가능한 지점

맨뒤부터 확인 ->
저장된 값보다 작으면 (구매 및 차익 계산)
저장된 값보다 크면 (맥스값 갱신)

"""

T = int(input())

for test_case in range(1, T+1):
    n = int(input())
    price = list(map(int, input().split()))
    max_price = price[n-1]
    result = 0
    for i in range(n-1, -1, -1):
        if price[i] < max_price:
            result += max_price - price[i]
        if price[i] > max_price:
            max_price = price[i]

    print("#"+str(test_case), result)

"""
퀵 정렬
피봇을 설정하고
피봇보다 작은 데이터와 큰 데이터의 위치를 바꾼다.

퀵정렬을 하기 전에는 피봇을 어떻게 설정할지에 대해서 미리 명시해야 한다.

예) 호어 분할 방식 - 피봇을 리스트의 첫번째 원소로 한다.
피봇을 기준으로 왼쪽부터 피봇보다 큰 값을 찾는다
리스트의 오른쪽에서부터 피봇보다 작은 값을 찾는다.
두 값을 교환해준다.
각각의 index가 교차할때 멈추고, 그 중 피봇보다 작은 값과 피봇을 교환한다.

피봇을 기준으로 왼쪽리스트, 오른쪽 리스트가 생성된다. 마찬가지로 실행한다.
분할된 리스트의 원소가 1개이면 종료한다.

"""

arr = [9, 3, 2, 6, 4, 1, 5, 0, 8, 7]

def quick_sort(array, start, end):
    # 원소가 1개이면 종료
    if start >= end:
        return
    pivot = start
    left = start+1
    right = end
    while left <= right:
        # 피벗보다 큰 값을 찾을 때까지
        while left <= end and array[left] <= array[pivot]:
            left += 1
        # 피벗보다 작은 값을 찾을 때까지
        while right >= start+1 and array[right] >= array[pivot]:
            right -= 1
        # 엇갈린다면
        if left > right:
            array[pivot], array[right] = array[right], array[pivot]
        else:
            array[left], array[right] = array[right], array[left]

    quick_sort(array, start, right-1)
    quick_sort(array, right+1, end)

quick_sort(arr, 0, len(arr)-1)

print(arr)



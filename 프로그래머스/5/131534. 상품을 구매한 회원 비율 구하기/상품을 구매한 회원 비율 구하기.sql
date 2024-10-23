--조건1 : 2021년에 가입한 전체 회원 - A테이블
--조건2 : 2021 상품을 구매한 회원의 수 / 2021 전체 회원
--조건3 : 년,월,별로 구분하여 비율을 출력
--조건4 : 회원비율 ROUND(###,2)
--조건5 : 정렬
--2021년에 가입한 회원 수 : 158명

/*****************************************/
SELECT TO_CHAR(A.SALES_DATE,'YYYY')     AS YEAR
     , EXTRACT(MONTH FROM A.SALES_DATE) AS MONTH --월(1,2,3..) 형태로 출력하기 ※RETURN값 숫자형임
     , COUNT(DISTINCT A.USER_ID)        AS PURCHASED_USERS --USER_ID중복을 제거하고 COUNT
     , ROUND(COUNT(DISTINCT A.USER_ID) / (SELECT COUNT(USER_ID)
                                            FROM USER_INFO
                                           WHERE TO_CHAR(JOINED, 'YYYY') = '2021'), 1) AS PUCHASED_RATIO
  FROM ONLINE_SALE A
  JOIN USER_INFO B ON A.USER_ID = B.USER_ID
 WHERE 1=1
   AND A.USER_ID = B.USER_ID
   AND TO_CHAR(B.JOINED,'YYYY') = '2021'
 GROUP
    BY TO_CHAR(A.SALES_DATE,'YYYY')
     , EXTRACT(MONTH FROM A.SALES_DATE)
 ORDER
    BY YEAR ASC
     , MONTH ASC
SELECT 
    CAR.CAR_ID, 
    CAR.CAR_TYPE, 
    FLOOR(CAR.DAILY_FEE * 30 * (1 - PLAN.DISCOUNT_RATE/100)) AS FEE
FROM 
    CAR_RENTAL_COMPANY_CAR CAR 
JOIN 
    CAR_RENTAL_COMPANY_DISCOUNT_PLAN PLAN 
    ON PLAN.CAR_TYPE = CAR.CAR_TYPE 
LEFT JOIN 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY HISTORY 
    ON CAR.CAR_ID = HISTORY.CAR_ID 
    AND (HISTORY.START_DATE <= TO_DATE('2022-11-30', 'YYYY-MM-DD') 
         AND HISTORY.END_DATE >= TO_DATE('2022-11-01', 'YYYY-MM-DD'))
WHERE 
    CAR.CAR_TYPE IN ('SUV', '세단') 
    AND HISTORY.HISTORY_ID IS NULL -- 해당 기간 동안 대여된 기록이 없는 자동차만 필터링
    AND PLAN.DURATION_TYPE = '30일 이상'
    AND FLOOR(CAR.DAILY_FEE * 30 * (1 - PLAN.DISCOUNT_RATE/100)) >= 500000 
    AND FLOOR(CAR.DAILY_FEE * 30 * (1 - PLAN.DISCOUNT_RATE/100)) < 2000000
ORDER BY 
    FEE DESC, 
    CAR.CAR_TYPE ASC, 
    CAR.CAR_ID DESC;
PROMPT ============================================================================================
SELECT 'Completed at: ' || TO_CHAR(SYSDATE,'YYYY-MM-DD-HH24-MI-SS') FROM dual
UNION ALL
SELECT 'Elapsed     : ' ||
       TO_CHAR(EXTRACT(HOUR   FROM elapsed), 'FM00') || ':' ||
       TO_CHAR(EXTRACT(MINUTE FROM elapsed), 'FM00') || ':' ||
       TO_CHAR(EXTRACT(SECOND FROM elapsed), 'FM00')
  FROM (SELECT NUMTODSINTERVAL(SYSDATE - TO_DATE('&start_time', 'YYYY-MM-DD-HH24-MI-SS'), 'DAY') elapsed FROM dual);
PROMPT ============================================================================================
SPOOL OFF
EXIT

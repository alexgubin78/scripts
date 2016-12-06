--BASIC
--TYPICAL
--All
--ALLSTATS LAST
select *
  from table(dbms_xplan.display_cursor('&sql_id', null, 'TYPICAL +ALLSTATS LAST +outline'))
/

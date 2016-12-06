set linesize 1000
set pagesize 1000
column compare_result format a11
column a$_object_type format a30
column a$_object_name format a50
column a$_status      format a20
column sh_object_type format a30
column sh_object_name format a50
column sh_status      format a20

SELECT COMPARE_RESULT
     , A$_OBJECT_TYPE
     , A$_OBJECT_NAME
     , A$_STATUS
     , SH_OBJECT_TYPE
     , SH_OBJECT_NAME
     , SH_STATUS
  FROM V_SCHEMA_STATISTICS_COMPARE
/

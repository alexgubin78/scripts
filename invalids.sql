set linesize 1000
set pagesize 1000

column object_type format a20
column object_name format a50
column errors      format a150

select uros.object_type
     , uros.object_name
     , listagg(ures.text, chr(13) || chr(10)) within group(order by ures.text) errors
  from user_objects uros
       join
       (select distinct name
             , type
             , text
          from user_errors) ures
         on uros.object_name = ures.name
        and uros.object_type = ures.type
 where status <> 'VALID'
 group by uros.object_type
        , uros.object_name
/

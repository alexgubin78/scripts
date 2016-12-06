set linesize 190
set pagesize 999
column object_name     format a30
column object_type     format a20
column owner           format a30
column oracle_username format a30
column os_user_name    format a30
column locked_mode     format a30

select do.object_name
     , do.object_type
     , do.owner
     , lo.oracle_username
     , lo.os_user_name
     , lo.locked_mode
  from v$locked_object lo
  join dba_objects do
    on lo.object_id = do.object_id
 order by 1
/

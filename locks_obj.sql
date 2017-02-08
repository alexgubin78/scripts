set linesize 200
set pagesize 999
column object_name      format a30
column object_type      format a20
column owner            format a30
column oracle_username  format a30
column os_user_name     format a30
column locked_mode      format a30
column locked_mode_name format a10

select do.object_name
     , do.object_type
     , do.owner
     , lo.oracle_username
     , lo.os_user_name
     , lo.locked_mode
     , decode(lo.locked_mode
             ,0, 'none'
             ,1, 'null'
             ,2, 'Row-S'
             ,3, 'Row-X'
             ,4, 'Share'
             ,5, 'S/Row-X'
             ,6, 'exclusive') as locked_mode_name
      , sq.sql_text
  from v$locked_object lo
       join
       dba_objects do
         on lo.object_id = do.object_id
       join
       v$session ss
         on lo.session_id = ss.sid
       left join
       v$sql sq
         on ss.sql_address = sq.address
 order by 1
/

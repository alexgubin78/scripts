set pagesize 9999
set linesize 300
column sid      format a10
column serial#  format a10
column program  format a50
column username format a30
column osuser   format a30
column machine  format a50

SELECT *
  FROM (SELECT s.sid
             , s.serial#
             , s.module
             , s.program
             , s.username
             , s.osuser
             , s.machine
             , ROUND(p.pga_alloc_mem/1048576) mb
          FROM v$session s
             , v$process p
         WHERE p.addr = s.paddr
         ORDER BY 5 DESC)
 WHERE ROWNUM < = 10
/

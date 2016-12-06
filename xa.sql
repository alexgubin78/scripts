set linesize 150
set pagesize 999

column local_tran_id format a40
column dbuser_owner  format a30
column host          format a40
column state         format a20

select d2ns.local_tran_id
     , d2ns.dbuser_owner
     , d2pg.host
     , d2pg.state
  from dba_2pc_pending d2pg
  join dba_2pc_neighbors d2ns
    on d2pg.local_tran_id = d2ns.local_tran_id
/

column machine  format a40
column osuser   format a20
column program  format a20
column sql_text format a60

select machine
     , sid
     , osuser
     , program
     , blocking_session
     , sql_text
  from (select lpad(' ', (level-1)*5, ' ') || vn.machine as machine
             , vn.sid
             , vn.osuser
             , vn.program
             , vn.blocking_session
             , vn.sql_id
             , vl.sql_text
             , level lv
             , connect_by_isleaf lf
          from v$session vn
               left join 
               v$sql vl
                 on vl.sql_id = vn.sql_id
       connect by prior vn.sid = vn.blocking_session
         start with vn.blocking_session is null)
where lv > 1 or lf = 0
/

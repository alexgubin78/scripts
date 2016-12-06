select table_name
     , index_name
     , to_char(last_analyzed, 'dd.mm.yyyy hh24:mm:mi') last_analyzed
     , status
     , compression
  from user_indexes
 where table_name like nvl(upper('%&table_name%'), table_name)
   and index_name like nvl(upper('%&index_name%'), index_name)
 order by table_name
        , index_name
/

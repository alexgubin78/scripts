select table_name
     , num_rows
     , blocks
     , avg_row_len
     , sample_size
     , last_analyzed
  from user_tables
 where table_name like upper('%&table_name%')
/

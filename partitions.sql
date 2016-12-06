
select table_name
     , composite
     , partition_name
     , subpartition_count
     , high_value
  from user_tab_partitions
 where table_name like upper('%&table_name%')
 order by table_name
        , partition_position
/

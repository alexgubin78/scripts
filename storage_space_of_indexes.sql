select table_owner
     , table_name
     , index_name
     , blocks
     , bytes
     , case
          when rn = 1 then sum_meg_bytes
          else null
       end sum_meg_bytes
  from (select ix.table_owner
             , ix.table_name
             , ix.index_name
             , ss.blocks
             , ss.bytes
             , ss.bytes/1024/1024 meg_bytes
             , sum(ss.bytes/1024/1024) over (partition by ix.table_owner, ix.table_name) sum_meg_bytes
             , row_number() over(partition by ix.table_owner, ix.table_name order by ix.table_owner, ix.table_name, ss.bytes desc) rn
          from all_indexes ix
               inner join
               dba_segments ss
                  on ix.index_name = ss.segment_name
                 and ix.table_owner = ss.owner
         where ix.table_name like upper('%&table_name%')
         order by ix.table_owner
                , ix.table_name
                , meg_bytes desc)
/
     

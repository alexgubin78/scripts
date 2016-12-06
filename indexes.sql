column fields format a40

select owner
     , index_name
     , (select listagg(column_name || ' ' || decode(descend, 'ASC', null, descend), ', ') within group(order by column_position)
          from all_ind_columns aics
         where aics.index_name  = alis.index_name
           and aics.index_owner = alis.owner
           and aics.table_name  = alis.table_name) fields
     , table_name
     , status
     , last_analyzed
     , visibility
  from all_indexes alis
 where alis.index_name = upper('&index_name')
/

column fields format a40

select urcs.constraint_name
     , urcs.table_name
     , urcs.constraint_type
     , (select listagg(column_name, ', ') within group(order by column_name)
          from user_cons_columns uccs
         where uccs.constraint_name = urcs.constraint_name
           and uccs.table_name = urcs.table_name) fields
     , case
         when urcs.constraint_type = 'R' then (select urcs.table_name 
                                                   || ' -----> '
                                                   || urcp.table_name
                                                 from user_constraints urcp 
                                                where urcs.r_constraint_name = urcp.constraint_name)
       end links
  from user_constraints urcs
 where urcs.constraint_name = upper('&constraint_name')
/

select *
  from (
select 'check'                    name, 'check schema' text from dual
union all
select 'locks'                    name, 'shows locks' text from dual
union all
select 'xa'                       name, 'shows xa transactions' text from dual
union all
select 'constr'                   name, 'shows info about constraint' text from dual
union all
select 'size'                     name, 'shows table sizes' text from dual
union all
select 'compile'                  name, 'recompile schema' text from dual
union all
select 'emails'                   name, 'show emails in our tables' text from dual
union all
select 'check_all'                name, 'check schema and emails' text from dual
union all
select 'locks_obj'                name, 'list of objects locks' text from dual
union all
select 'compare'                  name, 'compare statistics after importing a dump' text from dual
union all
select 'size_tab'                 name, 'shows size of tables' text from dual
union all
select 'fx_without_index'         name, 'shows fx without index' text from dual
union all
select 'partitions'               name, 'shows partitions' text from dual
union all
select 'storage_space_of_indexes' name, 'shows storage space of indexes' text from dual
union all
select 'index_last_analyzed'      name, 'shows index last analyzed' text from dual
union all
select 'table_last_analyzed'      name, 'shows table last analyzed' text from dual
union all
select 'table_analyze'            name, 'run table analyze' text from dual
union all
select 'sql_id'                   name, 'show sql_id by marker' text from dual
union all
select 'sql_explain'              name, 'show sql execution plan by sql_id' text from dual
union all
select 'indexes'                  name, 'show info about indexes' text from dual
union all
select 'vparams'                  name, 'show v$params' text from dual
)
order by name
/

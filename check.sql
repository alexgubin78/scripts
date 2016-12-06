select rpad('INVALID OBJECTS ', 20, '.') || count(*) res
  from user_objects
  where status <> 'VALID'
union all
select rpad('REPORTS', 20, '.') || count(*) res
  from table_templates
union all
select rpad('RULES', 20, '.') || count(*) res
  from table_rules
/

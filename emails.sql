column res format a200

select rpad('TABLE_PARAMS ', 30, '.') || listagg(val_string, ', ') within group(order by val_string) res
  from (select distinct val_string 
          from table_params
         where param_id like 'GED_NOTIFY%'
           and val_string is not null)
union all
select rpad('T1 ', 30, '.') || listagg(emails, ', ') within group(order by emails) res
  from (select distinct emails
          from t1
         where emails is not null)
union all
select rpad('REGIONS ', 30, '.') || listagg(mails, ', ') within group(order by mails) res
  from (select distinct mails 
          from regions
         where mail_group is not null)
union all
select rpad('CONFIGURATIONS ', 30, '.') || listagg(emails, ', ') within group(order by emails) res
  from (select distinct emails
          from configurations
         where emails is not null)
/

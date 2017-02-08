begin
  dbms_stats.gather_schema_stats (ownname => user);
end;
/

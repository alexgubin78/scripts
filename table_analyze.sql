begin 
  DBMS_STATS.GATHER_TABLE_STATS (
   	  ownname => user,
          tabname => '&table_name',
          estimate_percent => dbms_stats.auto_sample_size
          );
end;
/

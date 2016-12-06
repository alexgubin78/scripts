
column name          format a30
column value         format a78
column display_value format a78

select name
     , value
     , display_value
  from v$parameter
  where name in ('cursor_sharing'
                ,'db_file_multiblock_read_count'
                ,'optimizer_index_caching'
                ,'optimizer_index_cost_adj'
                ,'optimizer_mode'
                ,'pga_aggregate_target'
                ,'star_transformation_enabled'
                ,'optimizer_features_enable'
                ,'optimizer_dynamic_sampling'
                ,'user_dump_dest'
                ,'statistics_level'
                ,'control_management_pack_access'
                ,'result_cache_mode')
 order by name
/
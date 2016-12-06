
SELECT table_name
     , TRUNC(SUM(table_size)/1024/1024)      AS table_size_meg
     , TRUNC(SUM(index_size)/1024/1024)      AS index_size_meg
     , TRUNC(SUM(lobsegment_size)/1024/1024) AS lobsegment_size_meg
     , TRUNC(SUM(lobindex_size)/1024/1024)   AS lobindex_size_meg
     , TRUNC(SUM(table_size      +
                 index_size      +
                 lobsegment_size +
                 lobindex_size)/1024/1024)   AS sum_meg
  FROM (SELECT segment_name AS table_name 
             , bytes as table_size
             , 0     as index_size
             , 0     as lobsegment_size
             , 0     as lobindex_size
          FROM user_segments 
         WHERE segment_type = 'TABLE' 
         UNION ALL 
        SELECT i.table_name
             , 0       as table_size
             , s.bytes as index_size
             , 0       as lobsegment_size
             , 0       as lobindex_size
          FROM user_indexes i 
             , user_segments s 
         WHERE s.segment_name = i.index_name 
           AND s.segment_type = 'INDEX' 
         UNION ALL 
        SELECT l.table_name
             , 0       as table_size
             , 0       as index_size
             , s.bytes as lobsegment_size
             , 0       as lobindex_size
          FROM user_lobs l 
             , user_segments s 
         WHERE s.segment_name = l.segment_name 
           AND s.segment_type = 'LOBSEGMENT' 
         UNION ALL 
        SELECT l.table_name 
             , 0       as table_size
             , 0       as index_size
             , 0       as lobsegment_size
             , s.bytes as lobindex_size
          FROM user_lobs l 
             , user_segments s 
         WHERE s.segment_name = l.index_name 
           AND s.segment_type = 'LOBINDEX') 
 WHERE table_name LIKE UPPER('%&table_name%')
 GROUP BY table_name 
 ORDER BY sum_meg DESC
/

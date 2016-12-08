select object_type
     , status
     , count(*) cnt
  from user_objects
 group by object_type
        , status
/

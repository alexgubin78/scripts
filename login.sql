define _editor=vi
set serveroutput on size 1000000
set trimspool on
--set timing on
set long 5000
set linesize 9999
set pagesize 9999
column plan_plus_exp format a80
set sqlprompt '&_user.@&_connect_identifier.> '

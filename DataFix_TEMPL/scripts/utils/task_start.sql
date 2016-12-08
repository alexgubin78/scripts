ACCEPT owner_username PROMPT 'Enter &username username [&username]:' DEFAULT &username
ACCEPT owner_password PROMPT 'Enter &username password [&owner_username]: ' HIDE DEFAULT &owner_username

ACCEPT dbtns PROMPT 'Enter Database TNS Name: ' 
SET ECHO OFF
SET FEEDBACK OFF
SET VERIFY OFF
SET HEADING OFF
SET LINESIZE 132
WHENEVER SQLERROR EXIT SQL.SQLCODE
PROMPT Testing connection to &owner_username...
CONN &owner_username/&owner_password@&dbtns

COL system_time NEW_VALUE start_time;
SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD-HH24-MI-SS') system_time FROM dual;
SPOOL ../logs/&taskname-&start_time..log
PROMPT ============================================================================================
PROMPT Started task:       &taskname
PROMPT At:                 &start_time
PROMPT Database Instance:  &dbtns
PROMPT Database Username:  &owner_username
PROMPT ============================================================================================

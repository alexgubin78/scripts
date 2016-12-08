cd scripts
cmd /c sqlplus /nolog @./main_script.sql rollback user_owner
cd..
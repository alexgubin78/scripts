DEFINE taskname=&1
DEFINE username=&2

@./utils/task_start.sql

declare

  procedure pr_test_install
  is
  begin
    dbms_output.put_line('&taskname');
  end;

  procedure pr_install
  is
  begin
    dbms_output.put_line('&taskname');
  end;

  procedure pr_rollback
  is
    x number := 0;
  begin
    dbms_output.put_line('&taskname');
    for i in 1..1000000
    loop
      for n in 1..100
      loop
        x := n + i;
      end loop;
    end loop;
    dbms_output.put_line('x = ' || x);
  end;

  procedure pr_other
  is
  begin
    dbms_output.put_line('.');
    dbms_output.put_line('.WRONG TASKNAME');
    dbms_output.put_line('.');
  end;

begin  

  if '&taskname' = 'test_install' then
    pr_test_install;
  elsif '&taskname' = 'install' then
    pr_install;
  elsif '&taskname' = 'rollback' then
    pr_rollback;
  else
    pr_other;
  end if;

end;
/

@./utils/task_end.sql
connect 'jdbc:derby:WM;create=true;' user 'WM';
CALL SYSCS_UTIL.SYSCS_CREATE_USER('WM', 'WM');
disconnect;
exit;
connect 'jdbc:derby:WM;user=WM;password=WM;';
CREATE SCHEMA WM;
run 'src/main/resources/createDB.sql'
run 'src/main/resources/initDB.sql'
disconnect;
exit;
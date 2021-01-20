connect 'jdbc:derby:WM;create=true;' user 'WM';
CALL SYSCS_UTIL.SYSCS_CREATE_USER('WM', 'WM');
disconnect;
connect 'jdbc:derby:WM;user=WM;password=WM;';
CREATE SCHEMA WM;
run 'createDB.sql'
run 'initDB.sql'
disconnect;
exit;
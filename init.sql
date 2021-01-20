connect 'jdbc:derby://localhost:1527/WM;create=true;' user 'WM';
CALL SYSCS_UTIL.SYSCS_CREATE_USER('WM', 'WM');
run 'createDB.sql';
run 'initDB.sql';
disconnect;
exit;
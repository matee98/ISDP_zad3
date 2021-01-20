connect 'jdbc:derby:WM;user=WM;password=WM;';
CREATE SCHEMA WM;
run 'src/main/resources/createDB.sql'
run 'src/main/resources/initDB.sql'
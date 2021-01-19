connect 'jdbc:derby:WM;user=WM;password=WM;';
CREATE SCHEMA WM;
run 'src/main/resources/createDB.sql'
CREATE VIEW AUTHENTICATION_VIEW AS;
run 'src/main/resources/initDB.sql'
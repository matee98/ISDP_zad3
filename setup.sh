#!/usr/bin/expect

spawn ./ij
expect "ij>"
send "connect 'jdbc:derby:WM;create=true;' user 'WM';\r"
expect "ij>"
send "CALL SYSCS_UTIL.SYSCS_CREATE_USER('WM', 'WM');\r"
expect "ij>"
send "CREATE SCHEMA WM;\r"
expect "ij>"
send "run 'createDB.sql';\r"
expect "ij>"
send "run 'initDB.sql';\r"
expect "ij>"
send "disconnect;"
expect "ij>"
send "exit;"

interact
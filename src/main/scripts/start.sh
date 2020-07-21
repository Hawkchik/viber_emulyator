/opt/jdk1.8.0_171/bin/java -Duser.timezone=Europe/Moscow  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/ -jar ./viber_emul.jar & echo $! > ./pid.file &

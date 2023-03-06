clear
cp -r ../src/* .
javac */*.java
java -ea --add-opens java.base/java.util=ALL-UNNAMED -jar QueueTest.jar Nth

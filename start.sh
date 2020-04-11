cd /opt/housie
./mvnw spring-boot:run > log.log &
echo $! > pid
cat pid

cd /opt/housie
export secretKey="bla"
./mvnw spring-boot:run > log.log &
echo $! > pid
cat pid

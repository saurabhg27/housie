cd /opt/housie
export secretKey="bla"
./mvnw spring-boot:run > log.log 2>&1 &
echo $! > pid
cat pid

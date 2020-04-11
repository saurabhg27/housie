cd /opt/housie
echo "Trying to stop"
pid=`cat pid`
echo "pid is $pid"
kill $pid

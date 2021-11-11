#!/bin/sh

cd ~/DO378/labs/secure-tls/

echo "Starting the 'solver' project "
cd solver
mvn clean quarkus:dev -Ddebug=5005 &
SOLVER_PID=$!
sleep 5
cd ..

echo "Starting the 'adder' project "
cd adder
mvn clean quarkus:dev -Ddebug=5006 &
ADDER_PID=$!
sleep 5
cd ..

echo "Starting the 'multiplier' project "
cd multiplier
mvn clean quarkus:dev -Ddebug=5007 &
MULTIPLIER_PID=$!
sleep 5
cd ..

echo
read -p "Press enter to Terminate"
echo 
kill $SOLVER_PID $ADDER_PID $MULTIPLIER_PID
sleep 2
echo "All services terminated"
echo

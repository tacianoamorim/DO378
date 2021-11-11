#!/bin/bash

cd ~/DO378/solutions/secure-tls

echo "Exporting certificate for adder..."
keytool -exportcert -keystore adder/adder.keystore \
-storepass redhat -alias adder \
-rfc -file adder/adder.crt

echo "Exporting certificate for multiplier..."
keytool -exportcert -keystore multiplier/multiplier.keystore \
-storepass redhat -alias multiplier \
-rfc -file multiplier/multiplier.crt

echo "Exporting certificate for solver..."
keytool -exportcert -keystore solver/solver.keystore \
-storepass redhat -alias solver \
-rfc -file solver/solver.crt

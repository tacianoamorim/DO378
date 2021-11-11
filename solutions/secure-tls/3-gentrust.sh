#!/bin/bash

cd ~/DO378/solutions/secure-tls

echo "Importing certificate for adder into solver trust store..."
keytool -noprompt -keystore solver/solver.truststore \
-importcert -file adder/adder.crt \
-alias adder -storepass redhat

echo "Importing certificate for multiplier into solver trust store..."
keytool -noprompt -keystore solver/solver.truststore \
-importcert -file multiplier/multiplier.crt \
-alias multiplier -storepass redhat

echo "Importing certificate for solver into adder trust store..."
keytool -noprompt -keystore adder/adder.truststore \
-importcert -file solver/solver.crt \
-alias solver -storepass redhat

echo "Importing certificate for solver into multiplier trust store..."
keytool -noprompt -keystore multiplier/multiplier.truststore \
-importcert -file solver/solver.crt \
-alias solver -storepass redhat

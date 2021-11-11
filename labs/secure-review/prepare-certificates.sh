#!/bin/bash

pushd ~/DO378/labs/secure-review/quarkus-conference/

# KEYSTORE CREATIONS

echo "Generating keystore file for microservice-session..."
keytool -noprompt -genkeypair -keyalg RSA -keysize 2048 -validity 365 \
  -dname "CN=microservice-session,OU=Training, O=RedHat, L=Raleigh, ST=NC, C=US" \
  -ext "SAN:c=DNS:localhost,IP:127.0.0.1" \
  -alias microservice-session -storetype JKS \
  -storepass redhat -keypass redhat \
  -keystore microservice-session/microservice-session.keystore

echo "Generating keystore file for microservice-speaker..."
keytool -noprompt -genkeypair -keyalg RSA -keysize 2048 -validity 365 \
  -dname "CN=microservice-speaker,OU=Training, O=RedHat, L=Raleigh, ST=NC, C=US" \
  -ext "SAN:c=DNS:localhost,IP:127.0.0.1" \
  -alias microservice-speaker -storetype JKS \
  -storepass redhat -keypass redhat \
  -keystore microservice-speaker/microservice-speaker.keystore

echo "Generating keystore file for microservice-jwt..."
keytool -noprompt -genkeypair -keyalg RSA -keysize 2048 -validity 365 \
  -dname "CN=microservice-jwt,OU=Training, O=RedHat, L=Raleigh, ST=NC, C=US" \
  -ext "SAN:c=DNS:localhost,IP:127.0.0.1" \
  -alias microservice-jwt -storetype JKS \
  -storepass redhat -keypass redhat \
  -keystore microservice-jwt/microservice-jwt.keystore

# EXPORTING CERTIFICATES

echo "Exporting certificate for microservice-session..."
keytool -exportcert -keystore microservice-session/microservice-session.keystore \
  -storepass redhat -alias microservice-session \
  -rfc -file microservice-session/microservice-session.crt

echo "Exporting certificate for microservice-speaker..."
keytool -exportcert -keystore microservice-speaker/microservice-speaker.keystore \
  -storepass redhat -alias microservice-speaker \
  -rfc -file microservice-speaker/microservice-speaker.crt

echo "Exporting certificate for microservice-jwt..."
keytool -exportcert -keystore microservice-jwt/microservice-jwt.keystore \
  -storepass redhat -alias microservice-jwt \
  -rfc -file microservice-jwt/microservice-jwt.crt

# ADDING CERTIFICATES TO TRUSTSTORES FOR mTLS

echo "Importing certificate for microservice-session into microservice-speaker trust store..."
keytool -noprompt -keystore microservice-speaker/microservice-speaker.truststore \
  -importcert -file microservice-session/microservice-session.crt \
  -alias microservice-session -storepass redhat

echo "Importing certificate for microservice-speaker into microservice-session trust store..."
keytool -noprompt -keystore microservice-session/microservice-session.truststore \
  -importcert -file microservice-speaker/microservice-speaker.crt \
  -alias microservice-speaker -storepass redhat

popd

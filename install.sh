#!/bin/bash

openssl aes-256-cbc -K $encrypted_4d84b02f6973_key -iv $encrypted_4d84b02f6973_iv -in secring.gpg.enc -out /home/travis/secring.gpg -d

mkdir -p /home/travis/.gradle
cat << EOF > "/home/travis/.gradle/gradle.properties"
signing.keyId=$SIGNING_KEY_ID
signing.password=$SIGNING_PASSWORD
signing.secretKeyRingFile=/home/travis/secring.gpg

ossrhUsername=$OSSRH_USERNAME
ossrhPassword=$OSSRH_PASSWORD

github.token=$GITHUB_TOKEN
EOF

cat /home/travis/.gradle/gradle.properties
#!/bin/bash

mkdir -pf /home/travis/.gnupg/

openssl aes-256-cbc -K $encrypted_4d84b02f6973_key -iv $encrypted_4d84b02f6973_iv -in secring.gpg.enc -out /home/travis/.gnupg/secring.gpg -d
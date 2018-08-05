#!/usr/bin/env bash


ssh-keygen -t rsa -b 4096 -C "<your_email>" -f github_deploy_key -N ''
echo github_deploy_key.pub
rm github_deploy_key.pub
travis encrypt-file github_deploy_key
rm github_deploy_key
#! /bin/bash

sleep 15
curl -sS "http://v3.bdn.parabot.org/api/bot/create/provider?build_id=$TRAVIS_BUILD_ID&version=$PROVIDER_VERSION" >/dev/null
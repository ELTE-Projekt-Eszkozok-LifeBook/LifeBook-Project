#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "ELTE-Projekt-Eszkozok-LifeBook/LifeBook-Project" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "frontend" ]; then

  echo -e "Publishing javadoc...\n"

  cp -R LifeBookProject/LifeBookProject/target/site/apidocs $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/ELTE-Projekt-Eszkozok-LifeBook/LifeBook-Project gh-pages > /dev/null
  
  cd gh-pages
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc-latest ./javadoc
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"
  
fi
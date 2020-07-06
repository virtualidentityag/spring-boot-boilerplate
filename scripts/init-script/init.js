const replaceInFiles = require('replace-in-files');
const fs = require('fs');
const path = require('path');
const readlineSync = require('readline-sync');
const gitRemoteOriginUrl = require('git-remote-origin-url');

const sourceCompanyName = 'myCompanyName';
const sourceProjectName = 'myProjectName';

const targetCompanyName = sanitize(readlineSync.question('Please enter your projects COMPANY name [a-z0-9]: '));
console.log('Your generated companyName: ' + targetCompanyName);
const targetProjectName = sanitize(readlineSync.question('Please enter your PROJECT name [a-z0-9]: '));
console.log('Your generated projectName: ' + targetProjectName);

const rootDir = path.join(__dirname, '..', '..');
const sourceDir = path.join(rootDir, 'src', 'main', 'java', 'com');
const sourceCompanyDir = path.join(sourceDir, sourceCompanyName);
const targetCompanyDir = path.join(sourceDir, targetCompanyName);
const sourceProjectDir = path.join(targetCompanyDir, sourceProjectName);
const targetProjectDir = path.join(targetCompanyDir, targetProjectName);

const testDir = path.join(rootDir, 'src', 'test', 'java', 'com');
const sourceCompanyTestDir = path.join(testDir, sourceCompanyName);
const targetCompanyTestDir = path.join(testDir, targetCompanyName);
const sourceProjectTestDir = path.join(targetCompanyTestDir, sourceProjectName);
const targetProjectTestDir = path.join(targetCompanyTestDir, targetProjectName);

const integrationTestDir = path.join(rootDir, 'src', 'testIntegration', 'java', 'com');
const sourceCompanyIntegrationTestDir = path.join(integrationTestDir, sourceCompanyName);
const targetCompanyIntegrationTestDir = path.join(integrationTestDir, targetCompanyName);
const sourceProjectIntegrationTestDir = path.join(targetCompanyIntegrationTestDir, sourceProjectName);
const targetProjectIntegrationTestDir = path.join(targetCompanyIntegrationTestDir, targetProjectName);

function sanitize(input) {
  input = input.toLowerCase();
  input = input.replace(/[\W_]+/g,"");
  return input;
}

const filesForSearchAndReplace = [
  '../../src/**/**.java',
  '../../api/**',
  '../../build.gradle',
  '../../settings.gradle',
  '../../package.json',
  '../../package-lock.json'
]

async function replaceCompanyName() {
  await replace({
    files: filesForSearchAndReplace,

    from: /myCompanyName/g,
    to: targetCompanyName,

    optionsForFiles: {
      "ignore": [
        "**/node_modules/**"
      ]
    }
  });
}

async function replaceProjectName() {
  await replace({
    files: filesForSearchAndReplace,

    from: /myProjectName/g,
    to: targetProjectName,

    optionsForFiles: {
      "ignore": [
        "**/node_modules/**"
      ]
    }
  });
}

async function replaceGitUrl() {
  const gitUrl = await gitRemoteOriginUrl();

  await replace({
    files: ['../../package.json'],

    from: 'git@github.com:virtualidentityag/spring-boot-boilerplate.git',
    to: gitUrl
  });
}

async function replace(options) {
  try {
    const { countOfMatchesByPaths } = await replaceInFiles(options);
    console.log('Count of matches by paths:', countOfMatchesByPaths);
  } catch (error) {
    console.log('Error occurred:', error);
  }
}

async function run() {

  await replaceProjectName();
  await replaceCompanyName();
  await replaceGitUrl();


  fs.truncateSync('../../doc/CHANGELOG.md', 0, function(){console.log('done')});

  if(fs.existsSync(sourceCompanyDir)) {
    fs.renameSync(sourceCompanyDir, targetCompanyDir, function(err) {
      if (err) {
        console.log(err)
      } else {
        console.log("Successfully renamed the directory.")
      }
    });
  }

  if(fs.existsSync(sourceProjectDir)) {
    fs.renameSync(sourceProjectDir, targetProjectDir, function (err) {
      if (err) {
        console.log(err)
      } else {
        console.log("Successfully renamed the directory.")
      }
    });
  }

  if(fs.existsSync(sourceCompanyTestDir)) {
    fs.renameSync(sourceCompanyTestDir, targetCompanyTestDir, function(err) {
      if (err) {
        console.log(err)
      } else {
        console.log("Successfully renamed the test directory.")
      }
    });
  }

  if(fs.existsSync(sourceProjectTestDir)) {
    fs.renameSync(sourceProjectTestDir, targetProjectTestDir, function (err) {
      if (err) {
        console.log(err)
      } else {
        console.log("Successfully renamed the test directory.")
      }
    });
  }

  if(fs.existsSync(sourceCompanyIntegrationTestDir)) {
    fs.renameSync(sourceCompanyIntegrationTestDir, targetCompanyIntegrationTestDir, function(err) {
      if (err) {
        console.log(err)
      } else {
        console.log("Successfully renamed the integration test directory.")
      }
    });
  }

  if(fs.existsSync(sourceProjectIntegrationTestDir)) {
    fs.renameSync(sourceProjectIntegrationTestDir, targetProjectIntegrationTestDir, function (err) {
      if (err) {
        console.log(err)
      } else {
        console.log("Successfully renamed the integration test directory.")
      }
    });
  }

  return "done";
}

run();

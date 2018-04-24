
###How to run test with Gradle

Use the command --- || ./gradlew test ||--- to run all tests.

1) Wouldn’t it be nice to see how many tests were run, which passed, which failed, and how many were skipped?
With a small change to your build.gradle file you can.
Add this block to your build.gradle then re-run your tests on the command line.

tasks.withType(Test) {
  testLogging {
    exceptionFormat "full"
    events "started", "skipped", "passed", "failed"
    showStandardStreams true
  }
}

---\\\Folder Builds consists HTML reports about test results \\\-----

2) If you want to run your tests for only one build type (this can be important for a large test suite)
 you can use ./gradlew testDebug or ./gradlew testRelease.

Recomendations:

# Test one class
gradle test --tests *LibraryTest
gradle test -Dtest.single=LibraryTest
# Test one method
gradle test --tests *LibraryTest.testSomeLibraryMethod
# Test two methods
gradle test --tests *LibraryTest.testFoo --tests *LibraryTest.testBar
# Test multiple items with a wildcard (*)
gradle test --tests *Lib*
gradle test -Dtest.single=L*Test
# Test a specific Package and prefix
gradle test --tests my.company*.Library*

Re-run tests

# Deletes files created during test
gradle cleanTest test --tests *LibraryTest
# Or force the tasks to be re-run
gradle --rerun-tasks test --tests *LibraryTest
# Or clean the whole build before running the task
gradle clean test --tests *LibraryTest

Exclude a fixed set of tests
// Added in the build.gradle file
test {
    exclude '**/LegacyTest.class'
}

//Dynamic exclude through property defined in the build.gradle file
test {
    if (project.hasProperty('excludeTests')) {
        exclude project.property('excludeTests')
    }
}
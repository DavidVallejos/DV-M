# DV-M

This is a repository that was create for run a MT test case.

Commant lines helper:

Go to Project file path with the CMD command line. When It is run for the firt time You should put this command:

    mvn clean compile && mvn install -Dmaven.test.skip=true && mvn -pl "mach2" test

When It run more times you need to run with this command:

    mvn clean compile

press enter then:

    mvn -pl "mach2" test

Another tip is, run this project on a clean mach2's account. Without Boards

(I send you a PDF of how to run just a single feature)

Thanks, And Have a Fun

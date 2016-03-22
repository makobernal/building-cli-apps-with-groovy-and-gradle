To build:
$ ./gradlew clean build buildDeb

Install .deb and see results
$ sudo dpkg -i build/distributions/printwords_1_all.deb 
$ tree /opt/05-With-Nebula-OS-Package-Plugin/
$ ls -la /usr/bin/printwords

To run:
$  printwords -t 20 wow!
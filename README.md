# presenso-test

## Install and run the project
1. Create locally a directory. For example:

```
mkdir presenso
```
2. Move to the root folder. For example:

```
cd presenso
```
3. Run:

```
git clone https://github.com/vnikolaou/presenso-test.git
```
4. Move into the folder:

```
cd cd presenso-test
```
5. Run:

```
mvn compile exec:java -Dexec.args=3000 -Dexec.cleanupDaemonThreads=false
```

*NOTE:* in the example above the argument ```-Dexec.args=3000``` refers to the desired array size and it is just an example. You can modify it properly.

# Barren Land Analysis

### Overview
This is small Kotlin program that computes the areas of fertile land on a 400m by 600m farm.
It accepts a set of coordinates for barren fields and uses a breadth-first traversal to
search for each contiguous area of fertile land.

The program is simply run from the command line without arguments. 
The user is immediately prompted to input barren field coordinates, which will be 
accepted and analyzed as long each line input contains four integer coordinates. 

For example, inputting `0 292 399 307` and then an
empty line is valid input.

### Building and Running

#### Running the program
The easiest way to build and run the application is by using the gradle wrapper.

From the root of the project you can run on windows:

```
$ .\gradlew.bat run --console plain -q
```

or equivalently on linux/unix:
```
$ ./gradlew run --console plain -q
```
Note that the `--console plain -q` arguments are optional and simply suppress the normal gradle build output.  

The main function in `Main.kt` can also be run from an IDE.

#### Running the tests

Tests are run similarly with 
```
$ ./gradlew test
```
Or by invoking them directly in an IDE.

#### Static analysis

This program uses [ktlint](https://github.com/pinterest/ktlint) to detect possible bugs and style violations.

The linter can be invoked with:

```
$ ./gradlew ktlintCheck
```

### Remarks

Given a farm height of M and a farm width of N the runtime complexity
of the breadth-first traversal is _O(M*N)_, and the pre-processing on the barren fields is _O(N)_ in the number of fields. 

The space complexity is also _O(M*N)_ in the size of the farm and _O(N)_ in the number of barren fields.

This is perfectly acceptable 
for a bounded 400 x 600 input but the approach may be difficult to scale up to significantly larger land areas.

# mustache-cli
Yet another command line interface to mustache template engine written in Java.
It was built using [Jackson](https://github.com/FasterXML/jackson) and 
[Picocli](https://picocli.info) libraries.

## Install
JRE 11 and Maven are needed to compile and run mustache-cli. 

In order to generate an executable jar with all the dependencies, run

`mvn clean compile assembly:single`

It will generate a jar file inside target/ directory. Then you can run it directly by

`java -jar target/mustache-cli-<version>-jar-with-dependencies.jar [options]`

Also you can generate an alias inside your `~/.bashrc` file by appending the following line

```alias mustache-cli="java -jar path-to-project/target/mustache-cli-<version>-jar-with-dependencies.jar"```

And then simply run it inside a terminal by

`$ mustache-cli [options]`

## Usage

Run `mustache-cli -h` for help. It will print the following instructions

```
Usage: mustache-cli [-hV] -j=<json-file> -o=<output-file> -t=<template-file>
                    [-d=<delimiter> <delimiter>]...
Command line interface to mustache template engine.
  -d, --delimiters=<delimiter> <delimiter>
                           Custom delimiters
  -h, --help               Show this help message and exit.
  -j, --json=<json-file>   The json file used as input for the template
                             processing
  -o, --output=<output-file>
                           The output filename for the processed template
  -t, --template=<template-file>
                           The mustache template file
  -V, --version            Print version information and exit.
```
### Examples

* Basic usage:

`mustache-cli -j some-file.json -t some-template.mustache -o final-result.html`

* Using different "tags" delimiters:

`mustache-cli -j some-file.json -t some-template.mustache -o final-result.html -d "<%" "%>""`

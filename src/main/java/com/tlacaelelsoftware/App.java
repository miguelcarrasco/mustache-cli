package com.tlacaelelsoftware;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new MustacheCli()).execute(args);
        System.exit(exitCode);
    }
}

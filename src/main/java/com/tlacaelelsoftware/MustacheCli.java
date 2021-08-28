package com.tlacaelelsoftware;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import picocli.CommandLine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "mustache-cli", mixinStandardHelpOptions = true, version = "mustache-cli 1.0",
        description = "Command line interface for mustache template engine parsing")
public class MustacheCli implements Callable<Integer> {

    @CommandLine.Option(
            names = {"-t", "--template"},
            description =
                    "The mustache template file",
            paramLabel = "<template-file>",
            required = true
    )
    private File templateFile;

    @CommandLine.Option(
            names = {"-j", "--json"},
            description =
                    "The json file used as input for the template processing",
            paramLabel = "<json-file>",
            required = true
    )
    private File jsonFile;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description =
                    "The output filename for the processed template",
            paramLabel = "<output-file>",
            required = true
    )
    private File outputFile;

    @CommandLine.Option(names = {"-d", "--delimiters"}, description = "custom delimiters", arity = "2")
    private List<String> delimiters = Arrays.asList("{{", "}}");


    @Override
    public Integer call() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap
                = objectMapper.readValue(jsonFile, new TypeReference<>() {
        });

        DefaultMustacheFactory mf = new DefaultMustacheFactory();
        FileReader reader = new FileReader(templateFile);
        Mustache mustache = mf.compile(reader, templateFile.getName(), delimiters.get(0), delimiters.get(1));
        mustache.execute(new FileWriter(outputFile), jsonMap).flush();
        reader.close();

        return 0;
    }
}

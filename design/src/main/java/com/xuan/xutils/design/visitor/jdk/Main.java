package com.xuan.xutils.design.visitor.jdk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xuan
 * @since 2022/11/19
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/xuan");
        Files.walkFileTree(path, new DemoFileVisitor());
    }

}

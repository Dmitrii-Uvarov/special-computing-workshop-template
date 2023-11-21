package ru.spbu.apcyb.svp.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {

  public static void main(String[] args) {
    Path strctPath = Path.of(args[1]).resolve("file_structure.txt");
    String structure = buildTree(Path.of(args[0]));
    System.out.println(structure);
    try (var file = new FileWriter(strctPath.toFile())) {
      file.write(structure);
    } catch (IOException e){
      throw new RuntimeException("Failed to write directory structure tree to file",e);
    }
  }

  public static String buildTree(Path root) {
    StringBuilder sb = new StringBuilder();
    buildTreeRecursive(root, 0, sb);
    return sb.toString();
  }
  public static void buildTreeRecursive(Path root, int i, StringBuilder sb) {
    printDirectory(root,i,sb);
    try (var files = Files.list(root)) {
      files.forEach(p -> {
        if (Files.isDirectory(p)) {
          buildTreeRecursive(p,i+1,sb);
        } else {
          printFile(p,i+1,sb);
        }
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void printFile(Path file, int indent, StringBuilder sb) {
    sb.append(getIndentString(indent));
    sb.append("+--");
    sb.append(file.getFileName());
    sb.append(System.lineSeparator());
  }

  private static void printDirectory(Path dir, int indent, StringBuilder sb) {
    sb.append(getIndentString(indent));
    sb.append(dir.getFileName());
    sb.append("/");
    sb.append(System.lineSeparator());
  }

  private static String getIndentString(int indent) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < indent; i++) {
      sb.append("|  ");
    }
    return sb.toString();
  }
}

package ru.spbu.apcyb.svp.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {

  public static void main(String[] args) {
    Path r = Path.of(args[0]);
    Path d = Path.of(args[1]);
    getFileStructure(r, d);
  }

  /**
   * Builds a directory file tree and saves it to a .txt file.
   *
   * @param rootdir directory to build file tree for.
   * @param destdir directory to save the file tree. The tree will be saved in the file
   *                file_structure.txt.
   */
  public static void getFileStructure(Path rootdir, Path destdir) {
    Path strctPath = destdir.resolve("file_structure.txt");
    String structure = buildTree(rootdir);
    System.out.println(structure);
    try (var file = new FileWriter(strctPath.toFile())) {
      file.write(structure);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write directory structure tree to file", e);
    }
  }

  public static String buildTree(Path root) {
    StringBuilder sb = new StringBuilder();
    buildTreeRecursive(root, 0, sb);
    return sb.toString();
  }

  public static void buildTreeRecursive(Path root, int i, StringBuilder sb) {
    printDirectory(root, i, sb);
    try (var files = Files.list(root)) {
      files.forEach(p -> {
        if (Files.isDirectory(p)) {
          buildTreeRecursive(p, i + 1, sb);
        } else {
          printFile(p, i + 1, sb);
        }
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void printFile(Path file, int indent, StringBuilder sb) {
    sb.append(getIndentString(indent));
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
    if (indent == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < indent - 1; i++) {
      sb.append("│   ");
    }
    sb.append("├── ");
    return sb.toString();
  }
}

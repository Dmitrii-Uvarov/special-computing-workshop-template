package ru.spbu.apcyb.svp.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

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
    printDirectory(root, "", sb);
    buildTreeRecursive(root, "", sb);
    return sb.toString();
  }

  public static void buildTreeRecursive(Path file, String indent, StringBuilder sb) {
    try (var files = Files.list(file)) {
      Iterator<Path> it = files.iterator();
      while (it.hasNext()) {
        Path f = it.next();
        if (Files.isDirectory(f)) {
          printDirectory(f, indent + (it.hasNext() ? "├── " : "└── "), sb);
          buildTreeRecursive(f, indent + (it.hasNext() ? "│   " : "    "), sb);
        } else {
          printFile(f, indent + (it.hasNext() ? "├── " : "└── "), sb);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void printFile(Path file, String indent, StringBuilder sb) {
    sb.append(indent)
        .append(file.getFileName())
        .append(System.lineSeparator());
  }

  private static void printDirectory(Path file, String indent, StringBuilder sb) {
    sb.append(indent)
        .append(file.getFileName())
        .append("/")
        .append(System.lineSeparator());
  }
}


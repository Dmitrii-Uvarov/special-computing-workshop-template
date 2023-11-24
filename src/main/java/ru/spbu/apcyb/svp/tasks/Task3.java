package ru.spbu.apcyb.svp.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Task3 {

  private static final String FILE_EDGE = "├── ";
  private static final String LAST_FILE_EDGE = "└── ";
  private static final String DIR_INDENT = "│   ";
  private static final String LAST_DIR_INDENT = "    ";

  private static final String WRONG_MAIN_ARGS_MSG = """
      Incorrect amount of arguments. Should be two strings: path to directory that should be
      scanned and path to file where a file tree will be saved.
      """;
  private static final String FILE_TREE_WRITE_ERROR_MSG =
      "Failed to write directory structure tree to file";

  private static final String DIR_ENTRIES_ERROR_MSG = "Failed to get entries of the directory";

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException(WRONG_MAIN_ARGS_MSG);
    }
    Path r = Path.of(args[0]);
    Path d = Path.of(args[1]);
    getFileStructure(r, d);
  }

  /**
   * Builds a directory file tree and saves it to a .txt file.
   *
   * @param rootPath     directory path to build file tree for.
   * @param destFilePath txt file path to save the file tree.
   */
  public static void getFileStructure(Path rootPath, Path destFilePath) {
    String structure = buildTree(rootPath);
    System.out.println(structure);
    try (var file = new FileWriter(destFilePath.toFile())) {
      file.write(structure);
    } catch (IOException e) {
      throw new RuntimeException(FILE_TREE_WRITE_ERROR_MSG, e);
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
          printDirectory(f, indent + (it.hasNext() ? FILE_EDGE : LAST_FILE_EDGE), sb);
          buildTreeRecursive(f, indent + (it.hasNext() ? DIR_INDENT : LAST_DIR_INDENT), sb);
        } else {
          printFile(f, indent + (it.hasNext() ? FILE_EDGE : LAST_FILE_EDGE), sb);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(DIR_ENTRIES_ERROR_MSG,e);
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


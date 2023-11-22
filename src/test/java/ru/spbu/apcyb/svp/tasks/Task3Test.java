package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class Task3Test {

  @Test
  void directoryScanTest() throws IOException {
    String directoryPath = "src/test/resources/task3/TestDirectory";
    String treeFolder = "src/test/resources/task3";
    String pathToFile = "src/test/resources/task3/TestDirectory_expected.txt";

    String g = """ 
        TestDirectory/\r
        ├── empty/\r
        ├── foo/\r
        │   ├── another_one/\r
        │   │   └── another_one/\r
        │   │       └── another_one/\r
        │   │           └── another_one/\r
        │   │               └── another_one/\r
        │   │                   └── DJ_KHALED_WISE_WORDS.txt\r
        │   ├── bar.txt\r
        │   └── baz.txt\r
        └── some/\r
            ├── a.txt\r
            ├── b.txt\r
            ├── c.txt\r
            └── d/\r
                └── E.txt\r
                        """;

    try (FileWriter output = new FileWriter(pathToFile)) {
      output.write(g);
    }

    Path path = Path.of(directoryPath);
    Path filepath = Path.of(treeFolder);
    Task3.getFileStructure(path, filepath);

    Path expectedPathToResult = filepath.resolve("file_structure.txt");
    if (Files.notExists(expectedPathToResult)) {
      fail("Cannot find result file at expected path.");
    }

    assertEquals(-1, Files.mismatch(Path.of(pathToFile), expectedPathToResult));
  }
}

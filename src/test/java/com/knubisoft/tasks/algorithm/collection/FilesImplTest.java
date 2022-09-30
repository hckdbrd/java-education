package com.knubisoft.tasks.algorithm.collection;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FilesImplTest {
   private final Files instance = new FilesImpl();
   private final File emptyPath = Path.of("").toFile();


   private static String[] getFilesList(File sourceInDestinationDir) {
      return sourceInDestinationDir.list();
   }

   private static Path getSourceInDestinationPath(Path sourcePath, Path destinationPath) {
      Path sourceDirName = sourcePath.getName(1);
      return Path.of(destinationPath + "/" + sourceDirName);
   }

   @Test
   public void equalsContentTestFail() {
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(null, null));
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(emptyPath, null));
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(null, emptyPath));
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(emptyPath, emptyPath));
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/tests/algorithm/collection/ContentEqualsTestSuccessful.csv", numLinesToSkip = 1)
   @SneakyThrows
   public void contentEqualsTestSuccessful(String expected, String filePath1, String filePath2) {
      File file1 = Path.of(filePath1).toFile();
      File file2 = Path.of(filePath2).toFile();
      boolean equal = Boolean.parseBoolean(expected);
      assertEquals(equal, instance.contentEquals(file1, file2),
         "Expected that content is " + (equal ? "" : "not") + "equal, but was the opposite.");
   }

   @Test
   public void copyDirectoryToDirectoryTestFail() {
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(null, null));
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(emptyPath, null));
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(null, emptyPath));
      assertThrows(IllegalArgumentException.class, () -> instance.copyDirectoryToDirectory(emptyPath, emptyPath));
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/tests/algorithm/collection/CopyDirectoryToDirectoryIntegrityTestSuccessful.csv", numLinesToSkip = 1)
   @SneakyThrows
   public void copyDirectoryToDirectoryIntegrityTestSuccessful(String expected) {

      // 1) fetch input
      Path sourcePath = Path.of("/tests/algorithm/collection/copydirectory/source");
      Path destinationPath = Path.of("/tests/algorithm/collection/copydirectory/destination");
      File sourceDir = sourcePath.toFile();
      File destinationDir = destinationPath.toFile();

      // 2) copy the dir
      instance.copyDirectoryToDirectory(sourceDir, destinationDir);

      // 3) test for content integrity
      Path sourceInDestinationPath = getSourceInDestinationPath(sourcePath, destinationPath);
      File sourceInDestinationDir = sourceInDestinationPath.toFile();
      if (sourceInDestinationDir.exists()) {
         String[] expectedList = expected.split("\\|");
         assertArrayEquals(getFilesList(sourceInDestinationDir), expectedList,
            "Expected the following files in dir: " + expected + " but was: " + Arrays.toString(getFilesList(sourceInDestinationDir)) + ".");

         // 4) delete source folder in destination folder after test
         FileUtils.deleteDirectory(sourceInDestinationDir);
      }
   }

   @Test
   public void toStingFail() {
      assertThrows(NullPointerException.class, () -> instance.toString((URL) null, null));
      assertThrows(NullPointerException.class, () -> instance.toString((URL) null, Charset.defaultCharset()));
      assertThrows(NullPointerException.class, () -> instance.toString(new URL("https://example.com/"), null));
   }

   @Test
   public void toStingSuccessful() {

   }

   @Test
   public void toByteArrayFail() {
      assertThrows(NullPointerException.class, () -> instance.toByteArray(null));
   }

   @Test
   public void toByteArraySuccessful() {

   }

   @Test
   public void normalizeFail() {
      assertThrows(NullPointerException.class, () -> instance.normalize(null));
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/tests/algorithm/collection/NormalizeSuccessful.csv", numLinesToSkip = 1)
   public void normalizeSuccessful(String input, String expected) {
      String actual = instance.normalize(input);
      assertEquals(expected, actual,
         "Expected to get: '" + expected + "', but actual is: '" + actual + "'.");
   }

   @Test
   public void readLinesFail() {
      assertThrows(NullPointerException.class, () -> instance.readLines(null, null));
      assertThrows(IOException.class, () -> instance.readLines(emptyPath, Charset.defaultCharset()));
      assertThrows(FileNotFoundException.class,
         () -> instance.readLines(Path.of("/tests/algorithm/collection/isemptydirectory/empty1").toFile(),Charset.defaultCharset()));
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/tests/algorithm/collection/ReadLinesSuccessful.csv", numLinesToSkip = 1)
   @SneakyThrows
   public void readLinesSuccessful(String filePath, String expected) {
      File file = Path.of("src/main/resources/tests/algorithm/collection/readlines/lines.txt").toFile();
      List<String> expectedList = Stream.of(expected.split("\\|")).toList();
      assertEquals(expectedList, instance.readLines(file, Charset.defaultCharset()));
   }

   @Test
   public void isEmptyDirectoryFail() {
      assertThrows(NullPointerException.class, () -> instance.isEmptyDirectory(null));
      assertThrows(NotDirectoryException.class,
         () -> instance.isEmptyDirectory(Path.of("tests/algorithm/collection/isemptydirectory/notempty/some.txt").toFile()));
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/tests/algorithm/collection/IsEmptyDirectorySuccessful.csv", numLinesToSkip = 1)
   @SneakyThrows
   public void isEmptyDirectorySuccessful(String expected, String directoryPath) {
      boolean isEmpty = Boolean.parseBoolean(expected);
      File dir = Path.of(directoryPath).toFile();
      assertEquals(isEmpty, instance.isEmptyDirectory(dir),
         "Expected that '" + dir.getName() + "' directory is " + (isEmpty ? "" : "not") + "empty, but was opposite result.");
   }
}

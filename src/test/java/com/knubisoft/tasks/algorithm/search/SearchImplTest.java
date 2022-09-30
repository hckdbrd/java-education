package com.knubisoft.tasks.algorithm.search;

import com.knubisoft.tasks.algorithm.search.Search;
import com.knubisoft.tasks.algorithm.search.SearchImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchImplTest {
   private final Search instance = new SearchImpl();
   private static long[] getInputArray(String array) {
      return ArrayUtils.toPrimitive(Stream.of(array.split("")).map(Long::parseLong).toArray(Long[]::new));
   }

   @Test
   public void searchTestFail() {
      assertThrows(NullPointerException.class, () -> instance.binarySearch(null, 5));
      assertThrows(IllegalArgumentException.class, () -> instance.binarySearch(new long[0], 5));
   }

   @ParameterizedTest
   @CsvFileSource(resources = "/tests/algorithm/search/SearchTestSuccessful.csv", numLinesToSkip = 1)
   public void searchTestSuccessful(String array, String element, String expected) {
      long[] inputArray = getInputArray(array);
      long elementToSearch = Long.parseLong(element);
      int expectedPosition = Integer.parseInt(expected);
      int actualPosition = instance.binarySearch(inputArray, elementToSearch);

      assertEquals(
         expectedPosition, actualPosition,
         "Expected position is - " + expectedPosition + " but it's - " + actualPosition + "."
      );
   }
}

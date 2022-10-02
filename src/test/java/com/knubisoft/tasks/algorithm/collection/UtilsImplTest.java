package com.knubisoft.tasks.algorithm.collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsImplTest {

   private final Utils instance = new UtilsImpl();

   private HashMap<?, ?> generateIntStrMap(boolean reversedKV) {
      String[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
      int i = 1;

      if (reversedKV) {
         HashMap<String, Integer> map = new HashMap<>();
         for (String s : characters) {
            map.put(s, i);
            i++;
         }
         return map;
      } else {
         HashMap<Integer, String> map = new HashMap<>();
         for (String s : characters) {
            map.put(i, s);
            i++;
         }
         return map;
      }
   }

   @Test
   public void invertMapFail() {
      assertThrows(NullPointerException.class, () -> instance.invertMap(null));
   }

   @Test
   public void invertMapSuccessful() {
      HashMap<?, ?> inputMap = generateIntStrMap(false);
      HashMap<?, ?> expectedMap = generateIntStrMap(true);

      assertEquals(expectedMap, instance.invertMap(inputMap),
         "Expected that map should be: '" + expectedMap + "' , but was '" + inputMap + "'.");
   }

   @Test
   public void unionFail() {
      assertThrows(NullPointerException.class, () -> instance.union(null, null));
      assertThrows(NullPointerException.class, () -> instance.union(null, List.of(1, 2, 3, 4, 5)));
      assertThrows(NullPointerException.class, () -> instance.union(List.of(1, 2, 3, 4, 5), null));
   }

   @Test
   public void unionSuccessful() {
      List<Integer> l1 = List.of(1, 2, 3, 4, 5);
      List<Integer> l2 = List.of(6, 7, 8, 9, 0);
      List<Integer> expectedUnion = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
      List<Integer> actualUnion = instance.union(l1, l2);

      assertEquals(expectedUnion, actualUnion,
         "Expected that union should be: '" + expectedUnion + "' , but was '" + actualUnion + "'.");
   }

   @Test
   public void isEqualListFail() {
      List<Integer> l1 = List.of(6, 7, 8, 9, 0);
      boolean is1EqualNull = instance.isEqualList(null, l1);

      List<Integer> l3 = List.of(1, 2, 3, 4, 5);
      List<Integer> l4 = List.of(6, 7, 8, 9, 0);
      boolean is3Equal4 = instance.isEqualList(l3, l4);


      assertFalse(is1EqualNull,
         "Expected that lists should be: " + (is1EqualNull ? "" : "not") + " , equal but was opposite.");
      assertFalse(is3Equal4,
         "Expected that lists should be: " + (is3Equal4 ? "" : "not") + " , equal but was opposite.");
   }

   @Test
   public void isEqualListSuccessful() {
      List<Integer> l1 = List.of(1, 2, 3, 4, 5);
      List<Integer> l2 = List.of(1, 2, 3, 4, 5);
      boolean is1Equal2 = instance.isEqualList(l1, l2);
      boolean is3Equal4 = instance.isEqualList(null, null);

      assertTrue(is1Equal2,
         "Expected that lists should be: " + (is1Equal2 ? "" : "not") + " , equal but was opposite.");
      assertTrue(is3Equal4,
         "Expected that lists should be: " + (is3Equal4 ? "" : "not") + " , equal but was opposite.");
   }

   @Test
   public void synchronizedMapFail() {
      assertThrows(NullPointerException.class, () -> instance.synchronizedMap(Map.of()));
   }

   @Test
   public void synchronizedMapSuccessful() {
      assertEquals("{1=one, 2=two}", instance.synchronizedMap(Map.of(1, "one", 2, "two")).toString());
      assertEquals("{1=one}", instance.synchronizedMap(Map.of(1, "one")).toString());
   }

   @Test
   public void disjunctionFail() {
      assertThrows(NullPointerException.class, () -> instance.disjunction(null, null));
      assertThrows(NullPointerException.class, () -> instance.disjunction(null, List.of(1, 2, 3, 4, 5)));
      assertThrows(NullPointerException.class, () -> instance.disjunction(List.of(1, 2, 3, 4, 5), null));
   }

   @Test
   public void disjunctionSuccessful() {
      List<Integer> l1 = List.of(1, 2, 3, 4, 5);
      List<Integer> l2 = List.of(3, 4);
      List<Integer> expectedList = List.of(1, 2, 5);
      List<Integer> actualList = (List<Integer>) instance.disjunction(l1, l2);

      assertEquals(expectedList, actualList,
         "Expected that union should be: '" + expectedList + "' , but was '" + actualList + "'.");
   }

   @Test
   public void subtractFail() {
      assertThrows(NullPointerException.class, () -> instance.subtract(null, null));
      assertThrows(NullPointerException.class, () -> instance.subtract(null, List.of(1, 2, 3, 4, 5)));
      assertThrows(NullPointerException.class, () -> instance.subtract(List.of(1, 2, 3, 4, 5), null));
   }

   @Test
   public void subtractSuccessful() {
      List<Character> l1 = List.of('A', 'A', 'A', 'C', 'B', 'B');
      List<Character> l2 = List.of('A', 'A', 'B', 'B');
      List<Character> expectedList = List.of('A', 'C');
      List<Character> actualList = (List<Character>) instance.subtract(l1, l2);

      assertEquals(expectedList, actualList,
         "Expected that union should be: '" + expectedList + "' , but was '" + actualList + "'.");
   }

   @Test
   public void chainedComparatorFail() {
      assertThrows(NullPointerException.class, () -> instance.chainedComparator( null));
   }

   @Test
   public void chainedComparatorSuccessful() {

   }

   @Test
   public void isSubCollectionFail() {
      assertThrows(NullPointerException.class, () -> instance.isSubCollection(null, null));
      assertThrows(NullPointerException.class, () -> instance.isSubCollection(null, List.of(1, 2, 3, 4, 5)));
      assertThrows(NullPointerException.class, () -> instance.isSubCollection(List.of(1, 2, 3, 4, 5), null));
   }

   @Test
   public void isSubCollectionSuccessful() {
      List<Character> l1 = List.of('A', 'A', 'A', 'C', 'B', 'B');
      List<Character> l2 = List.of('A', 'A', 'B', 'B');
      boolean is1SubColl2 = instance.isSubCollection(l2, l1);

      assertTrue(is1SubColl2,
         "Expected that lists should be: " + (is1SubColl2 ? "" : "not") + " , sub collection but was opposite.");
   }
}

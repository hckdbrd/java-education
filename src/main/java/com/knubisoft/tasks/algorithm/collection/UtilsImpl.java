package com.knubisoft.tasks.algorithm.collection;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilsImpl implements Utils {
   @Override
   public <K, V> Map<V, K> invertMap(Map<K, V> map) {
      if (map == null) {
         throw new NullPointerException();
      }
      return map.entrySet()
         .stream()
         .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (a, b) -> a));
   }

   @Override
   public <E> List<E> union(List<? extends E> list1, List<? extends E> list2) {
      if (list1 == null || list2 == null) {
         throw new NullPointerException();
      }
      return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
   }

   @Override
   public boolean isEqualList(Collection<?> list1, Collection<?> list2) {
      if (list1 == null && list2 == null) {
         return true;
      }

      if (list1 instanceof List && list2 instanceof List) {
         return list1.equals(list2);
      }

      return false;
   }

   @Override
   public <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
      return null;
   }

   @Override
   public <O> Collection<O> disjunction(Iterable<? extends O> a, Iterable<? extends O> b) {
      if (a == null || b == null) {
         throw new NullPointerException();
      }
      return CollectionUtils.disjunction(a, b);
   }

   @Override
   public <O> Collection<O> subtract(Iterable<? extends O> a, Iterable<? extends O> b) {
      if (a == null || b == null) {
         throw new NullPointerException();
      }
      return CollectionUtils.subtract(a, b);
   }

   @Override
   public <E> Comparator<E> chainedComparator(Comparator<E>... comparators) {
      if (comparators == null) throw new NullPointerException();
      return null;
   }

   @Override
   public boolean isSubCollection(Collection<?> a, Collection<?> b) {
      if (a == null || b == null) {
         throw new NullPointerException();
      }
      return CollectionUtils.isSubCollection(a, b);
   }
}
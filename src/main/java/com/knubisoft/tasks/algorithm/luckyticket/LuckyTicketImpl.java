package com.knubisoft.tasks.algorithm.luckyticket;

import java.util.Arrays;

public class LuckyTicketImpl implements LuckyTicket {

   @Override
   public boolean checkIsLuckyTicket(String ticket) {

      if (ticket == null) {
         throw new NullPointerException();
      }

      if (!ticket.matches("\\b\\d+\\b")) {
         throw new IllegalArgumentException("Ticket number must be ONLY NUMERIC. Yours - " + ticket);
      }

      if (!ticket.matches(".{6,8}")) {
         throw new IllegalArgumentException("Ticket number must have length 6-8. Yours - " + ticket.length());
      }

      int lastIndex = ticket.length() - 1;
      char[] array = ticket.toCharArray();
      char[] reversedArray = new char[array.length];

      for (int i = 0; i <= lastIndex; i++) {
         reversedArray[i] = array[lastIndex - i];
      }

      return Arrays.equals(array, reversedArray);
   }
}

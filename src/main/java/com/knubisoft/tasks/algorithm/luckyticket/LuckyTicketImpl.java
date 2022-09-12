package com.knubisoft.tasks.algorithm.luckyticket;

import java.util.Arrays;

public class LuckyTicketImpl implements LuckyTicket {
    @Override
    public boolean checkIsLuckyTicket(String ticket) {
        if (ticket == null || !ticket.matches("\\d{6,8}")) return false;

        char[] array = ticket.toCharArray();
        int lastIndex = array.length-1;

        char[] reversedArray = new char[array.length];
        for (int i = 0; i <= lastIndex; i++) {
            reversedArray[i] = array[lastIndex-i];
        }

        return Arrays.equals(array,reversedArray);
    }
}

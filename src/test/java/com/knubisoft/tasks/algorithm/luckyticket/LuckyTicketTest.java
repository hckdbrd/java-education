package com.knubisoft.tasks.algorithm.luckyticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class LuckyTicketTest {
    private final LuckyTicket instance = new LuckyTicketImpl();
    @Test
    public void luckyTicketsAreEmpty() {
        assertThrows(NullPointerException.class, () -> instance.checkIsLuckyTicket(null));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/algorithm/luckyticket/LuckyTicketsIsNumeric.csv", numLinesToSkip = 1)
    public void luckyTicketsAreNumeric(String input) {
        assertThrows(IllegalArgumentException.class, () -> instance.checkIsLuckyTicket(input));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/algorithm/luckyticket/LuckyTicketsIsRequiredLength.csv", numLinesToSkip = 1)
    public void luckyTicketsAreRequiredLength(String input) {
        assertThrows(IllegalArgumentException.class, () -> instance.checkIsLuckyTicket(input));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/algorithm/luckyticket/LuckyTicketTest.csv", numLinesToSkip = 1)
    public void luckyTicketsTest(String input, String expected) {
        assertEquals(expected, instance.checkIsLuckyTicket(input));
    }
}
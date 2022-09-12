package com.knubisoft.tasks.algorithm.luckyticket;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class LuckyTicketTest {
    private final LuckyTicket instance = new LuckyTicketImpl();
    /**
     * TODO optimize tests visibility
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/luckyticket.csv", numLinesToSkip = 1)
    public void luckyTicketsTest(String input, String expected) {
        // Test for null
        // Test for size
        // Test for equals
        assertEquals(Boolean.valueOf(expected), instance.checkIsLuckyTicket(input));
    }
}
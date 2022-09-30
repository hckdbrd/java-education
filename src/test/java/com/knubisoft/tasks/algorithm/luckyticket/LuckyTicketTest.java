package com.knubisoft.tasks.algorithm.luckyticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class LuckyTicketTest {
    private final LuckyTicket instance = new LuckyTicketImpl();
    @Test
    public void luckyTicketTestFail() {
        assertThrows(NullPointerException.class, () -> instance.checkIsLuckyTicket(null));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/algorithm/luckyticket/LuckyTicketsRequirementsTest.csv", numLinesToSkip = 1)
    public void luckyTicketsRequirementsTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> instance.checkIsLuckyTicket(input));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/tests/algorithm/luckyticket/LuckyTicketsTestSuccessful.csv", numLinesToSkip = 1)
    public void luckyTicketsTestSuccessful(String input, String result) {
        boolean expected = Boolean.parseBoolean(result);
        boolean actual = instance.checkIsLuckyTicket(input);
        assertEquals(expected, actual,
           "Expected that " + actual + " is " + (expected ? " lucky" : " not lucky") +" but it was " + (!expected ? " lucky" : " not lucky") + ".");
    }
}
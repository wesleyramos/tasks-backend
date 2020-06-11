package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void shouldReturnTrueParaDatasFuturas() {
        LocalDate date = LocalDate.now().plusDays(1L);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnFalseParaDatasPassadas() {
        LocalDate date = LocalDate.now().minusDays(1L);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnTrueParaDatasPresente() {
        LocalDate date = LocalDate.now();
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

}
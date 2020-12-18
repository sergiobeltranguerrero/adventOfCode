import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void part0() {
    }

    @Test
    void part1() {
    }

    @Test
    void validateFalsePassports() throws IOException {
        Day4 day4 = new Day4("resources/Day4.txt");
        assertFalse(day4.validate("byr", "1900"));
        assertFalse(day4.validate("iyr", "20200"));
        assertFalse(day4.validate("eyr", "120"));
        assertFalse(day4.validate("hgt", "120in"));
        assertFalse(day4.validate("hgt", "1cm"));
        assertFalse(day4.validate("hgt", "120"));
        assertFalse(day4.validate("hcl", "@abc167"));
        assertFalse(day4.validate("hcl", "#azc167"));
        assertFalse(day4.validate("hcl", "#azc16"));
        assertFalse(day4.validate("hcl", "#azc162333"));
        assertFalse(day4.validate("ecl", ""));
        assertFalse(day4.validate("ecl", "abc"));
        assertFalse(day4.validate("pid", "1234566700"));
    }

    @Test
    void validatePassports() throws IOException {
        Day4 day4 = new Day4("resources/Day4.txt");
        assertTrue(day4.validate("byr", "2002"));
        assertTrue(day4.validate("iyr", "2018"));
        assertTrue(day4.validate("eyr", "2021"));
        assertTrue(day4.validate("hgt", "60in"));
        assertTrue(day4.validate("hgt", "170cm"));
        assertTrue(day4.validate("hcl", "#abc167"));
        assertTrue(day4.validate("ecl", "amb"));
        assertTrue(day4.validate("ecl", "oth"));
        assertTrue(day4.validate("pid", "000000001"));
    }
}
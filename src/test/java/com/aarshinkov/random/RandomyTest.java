package com.aarshinkov.random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RandomyTest {

  private Randomy randomy;

  @BeforeEach
  void setUp() {
    randomy = Randomy.Builder.build();
    assertThat(randomy).isNotNull();
  }

  @DisplayName("Generate random string with zero length")
  @Test
  void generateRandomStringLengthZero() {

    final String result = randomy.generateRandomString(0);
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @DisplayName("Generate random string with length of one")
  @Test
  void generateRandomStringLengthOne() {

    final String result = randomy.generateRandomString(1);
    assertThat(randomy.hasDigitInString(result)).isFalse();
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
  }

  @DisplayName("Generate random string with length of two")
  @Test
  void generateRandomStringLengthTwo() {

    final String result = randomy.generateRandomString(2);
    assertThat(randomy.hasDigitInString(result)).isFalse();
    assertThat(result).isNotNull();
    assertThat(result).hasSize(2);
  }

  @DisplayName("Generate random string with negative length")
  @Test
  void generateRandomStringLengthNegative() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomString(-3);
    });
  }

  @DisplayName("Generate random string with negative length and added digits")
  @RepeatedTest(5)
  @Test
  void generateRandomStringLengthNegativeAndDigits() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomString(-2, true);
    });
  }

  @DisplayName("Generate random string with zero length and added digits")
  @RepeatedTest(5)
  @Test
  void generateRandomStringLengthZeroAndDigits() {

    final String result = randomy.generateRandomString(0, true);
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @DisplayName("Generate random string with length of one and added digits")
  @RepeatedTest(5)
  @Test
  void generateRandomStringLengthOneAndDigits() {

    final String result = randomy.generateRandomString(1, true);
    assertThat(randomy.hasDigitInString(result)).isTrue();
    assertThat(result).isNotNull();
    assertThat(result).hasSize(1);
  }

  @DisplayName("Generate random string with length of two and added digits")
  @RepeatedTest(5)
  @Test
  void generateRandomStringLengthTwoAndDigits() {

    final String result = randomy.generateRandomString(2, true);
    assertThat(randomy.hasDigitInString(result)).isTrue();
    assertThat(result).isNotNull();
    assertThat(result).hasSize(2);
  }

  @DisplayName("Generate random string with length of two added digits and only lowercase")
  @RepeatedTest(5)
  @Test
  void generateRandomStringLengthTwoAndAddedDigitsOnlyLowercase() {

    final String result = randomy.generateRandomString(2, true, false, true);
    assertThat(randomy.hasDigitInString(result)).isTrue();
    assertThat(result).isNotNull();
    assertThat(result).hasSize(2);
  }

  @DisplayName("Generate random string with length negative")
  @RepeatedTest(5)
  @Test
  void generateRandomStringLengthNegativeOverloaded() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomString(-3, true, false, true);
    });
  }

  @DisplayName("Has digit in string when string is NULL")
  @Test
  void hasDigitInStringNull() {

    final boolean result = randomy.hasDigitInString(null);
    assertThat(result).isFalse();
  }

  @DisplayName("Has digit in string when string is empty")
  @Test
  void hasDigitInStringEmpty() {

    final boolean result = randomy.hasDigitInString("");
    assertThat(result).isFalse();
  }

  @DisplayName("Has digit in string when string contains one digit")
  @Test
  void hasDigitInStringContainsOneDigit() {

    final boolean result = randomy.hasDigitInString("dk9fERgd");
    assertThat(result).isTrue();
  }

  @DisplayName("Has digit in string when string contains two digits")
  @Test
  void hasDigitInStringContainsTwoDigits() {

    final boolean result = randomy.hasDigitInString("dk9fER6gd");
    assertThat(result).isTrue();
  }

  @DisplayName("Has digit in string when string contains no digits")
  @Test
  void hasDigitInStringContainsNoDigits() {

    final boolean result = randomy.hasDigitInString("dkDWkgOOrgd");
    assertThat(result).isFalse();
  }

  @DisplayName("Has digit in string when string contains only spaces")
  @Test
  void hasDigitInStringContainsOnlySpaces() {

    final boolean result = randomy.hasDigitInString("      ");
    assertThat(result).isFalse();
  }

  //----------------------------------------------------

  @DisplayName("Has only lowercase letters, sequence null")
  @Test
  void hasOnlyLowercaseLettersSequenceNull() {
    final boolean result = randomy.hasOnlyLowercase(null);
    assertThat(result).isFalse();
  }

  @DisplayName("Has only lowercase letters, sequence empty")
  @Test
  void hasOnlyLowercaseLettersSequenceEmpty() {
    final boolean result = randomy.hasOnlyLowercase("");
    assertThat(result).isFalse();
  }

  @DisplayName("Has only lowercase letters, sequence only spaces")
  @Test
  void hasOnlyLowercaseLettersSequenceOnlySpaces() {
    final boolean result = randomy.hasOnlyLowercase("    ");
    assertThat(result).isFalse();
  }

  @DisplayName("Has only lowercase letters, all lowercase")
  @Test
  void hasOnlyLowercaseLettersAllLower() {
    final boolean result = randomy.hasOnlyLowercase("gjoerijger");
    assertThat(result).isTrue();
  }

  @DisplayName("Has only lowercase letters, one uppercase")
  @Test
  void hasOnlyLowercaseLettersOneUpper() {
    final boolean result = randomy.hasOnlyLowercase("gjoerEijger");
    assertThat(result).isFalse();
  }

  @DisplayName("Has only lowercase letters, all lowercase and special characters")
  @Test
  void hasOnlyLowercaseLettersAllLowerAndSpecialCharacters() {
    final boolean result = randomy.hasOnlyLowercase("gjoer#%@ijger");
    assertThat(result).isTrue();
  }

  @DisplayName("Has only lowercase letters, one uppercase and special characters")
  @Test
  void hasOnlyLowercaseLettersOneUpperAndSpecialCharacters() {
    final boolean result = randomy.hasOnlyLowercase("gjoEr#!op@ijger");
    assertThat(result).isFalse();
  }

  //----------------------------------------------------

  @DisplayName("Has only uppercase letters, sequence null")
  @Test
  void hasOnlyUppercaseLettersSequenceNull() {
    final boolean result = randomy.hasOnlyUppercase(null);
    assertThat(result).isFalse();
  }

  @DisplayName("Has only uppercase letters, sequence empty")
  @Test
  void hasOnlyUppercaseLettersSequenceEmpty() {
    final boolean result = randomy.hasOnlyUppercase("");
    assertThat(result).isFalse();
  }

  @DisplayName("Has only uppercase letters, sequence only spaces")
  @Test
  void hasOnlyUppercaseLettersSequenceOnlySpaces() {
    final boolean result = randomy.hasOnlyUppercase("    ");
    assertThat(result).isFalse();
  }

  @DisplayName("Has only uppercase letters, all uppercase")
  @Test
  void hasOnlyUppercaseLettersAllUppercase() {
    final boolean result = randomy.hasOnlyUppercase("DWGJRSOREGOIEJG");
    assertThat(result).isTrue();
  }

  @DisplayName("Has only uppercase letters, one lowercase")
  @Test
  void hasOnlyUppercaseLettersOneUpper() {
    final boolean result = randomy.hasOnlyUppercase("EGNWEIORUGnEOR");
    assertThat(result).isFalse();
  }

  @DisplayName("Has only uppercase letters, all uppercase and special characters")
  @Test
  void hasOnlyUppercaseLettersAllUppercaseAndSpecialCharacters() {
    final boolean result = randomy.hasOnlyUppercase("WDJWOG&$%M@Z#(");
    assertThat(result).isTrue();
  }

  @DisplayName("Has only uppercase letters, one lowercase and special characters")
  @Test
  void hasOnlyUppercaseLettersOneLowercaseAndSpecialCharacters() {
    final boolean result = randomy.hasOnlyUppercase("EGNeI%$*KF");
    assertThat(result).isFalse();
  }

  // From v1.2.0
  @DisplayName("Generate number between 0 and 1 inclusive")
  @RepeatedTest(10)
  @Test
  void generateRandomNumberBetweenZeroAndOneInclusive() {

    Integer start = 0;
    Integer end = 1;

    Integer result = randomy.generateRandomNumber(start, end);
    assertThat(result).isNotNull();
    assertThat(result).isGreaterThanOrEqualTo(start);
    assertThat(result).isLessThanOrEqualTo(end);
  }

  @DisplayName("Generate number between numbers inclusive")
  @RepeatedTest(10)
  @Test
  void generateRandomNumberBetweenNumbersInclusive() {
    Integer start = 0;
    Integer end = 20;

    Integer result = randomy.generateRandomNumber(start, end);
    assertThat(result).isNotNull();
    assertThat(result).isGreaterThanOrEqualTo(start);
    assertThat(result).isLessThanOrEqualTo(end);

    start = 60;
    end = 99;

    result = randomy.generateRandomNumber(start, end);
    assertThat(result).isNotNull();
    assertThat(result).isGreaterThanOrEqualTo(start);
    assertThat(result).isLessThanOrEqualTo(end);

    start = 563;
    end = 1050;

    result = randomy.generateRandomNumber(start, end);
    assertThat(result).isNotNull();
    assertThat(result).isGreaterThanOrEqualTo(start);
    assertThat(result).isLessThanOrEqualTo(end);
  }

  @DisplayName("Generate number, parameters null, throw illegal argument exception")
  @Test
  void generateRandomNumberParametersNull() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(null, null);
    });
  }

  @DisplayName("Generate number, end parameter null, throw illegal argument exception")
  @Test
  void generateRandomNumberOneParameterNull() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(1, null);
    });
  }

  @DisplayName("Generate number, start parameter null, throw illegal argument exception")
  @Test
  void generateRandomNumberEndParameterNull() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(null, 20);
    });
  }

  @DisplayName("Generate number, start parameter greater than end number, throw illegal argument exception")
  @Test
  void generateRandomNumberStartParameterGreaterThanEndNumber() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(25, 20);
    });
  }

  @DisplayName("Generate number, start parameter greater than end number with one, throw illegal argument exception")
  @Test
  void generateRandomNumberStartParameterGreaterThanEndNumberDiffOne() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(21, 20);
    });
  }

  // Generate number end exclusive

  @DisplayName("Generate number, end exclusive")
  @RepeatedTest(20)
  @Test
  void generateRandomNumbersEndExclusive() {
    Integer start = 0;
    Integer end = 20;

    Integer result = randomy.generateRandomNumber(start, end, false);
    assertThat(result).isNotNull();
    assertThat(result).isGreaterThanOrEqualTo(start);
    assertThat(result).isLessThan(end);

    start = 65;
    end = 87;

    result = randomy.generateRandomNumber(start, end, false);
    assertThat(result).isNotNull();
    assertThat(result).isGreaterThanOrEqualTo(start);
    assertThat(result).isLessThan(end);
  }

  @DisplayName("Generate number, parameters null, throw illegal argument exception, end exclusive")
  @Test
  void generateRandomNumberParametersNullEndExclusive() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(null, null, false);
    });
  }

  @DisplayName("Generate number, end parameter null, throw illegal argument exception, end exclusive")
  @Test
  void generateRandomNumberOneParameterNullEndExclusive() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(1, null, false);
    });
  }

  @DisplayName("Generate number, start parameter null, throw illegal argument exception, end exclusive")
  @Test
  void generateRandomNumberEndParameterNullEndExclusive() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(null, 20, false);
    });
  }

  @DisplayName("Generate number, start parameter greater than end number, throw illegal argument exception, end exclusive")
  @Test
  void generateRandomNumberStartParameterGreaterThanEndNumberEndExclusive() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(25, 20, false);
    });
  }

  @DisplayName("Generate number, start parameter greater than end number with one, throw illegal argument exception, end exclusive")
  @Test
  void generateRandomNumberStartParameterGreaterThanEndNumberDiffOneEndExclusive() {

    IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
      randomy.generateRandomNumber(21, 20, false);
    });
  }
}
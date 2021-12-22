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
}
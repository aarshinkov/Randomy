package com.aarshinkov.random;

import java.security.SecureRandom;

public class Randomy {

  private final java.util.Random RANDOM = new SecureRandom();
  private final String DIGITS = "0123456789";
  private final String CAPITAL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  private final String ALPHABET = CAPITAL_ALPHABET + LOWERCASE_ALPHABET;
  private final String SPECIAL_CHARACTERS = "~`'\"!@#$%^&*()-_+=,.;\\|{}[]";
//  private final String DIG_ALPHA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  private Randomy() {

  }

  public static class Builder {

    public static Randomy build() {
      return new Randomy();
    }
  }

  /**
   * Takes length as a parameter and returns a random string with the english
   * alphabet. If this method is used it will return a string with no digits in it.
   *
   * @param length the length of the string as characters
   *
   * @return the randomly generated string
   */
  public String generateRandomString(int length) {

    if (length < 0) {
      throw new IllegalArgumentException("Length must not be negative");
    }

    return generateRandomString(length, false);
  }

  /**
   * Takes length as a parameter and returns a random string with the english
   * alphabet
   *
   * @param length        the length of the string as characters
   * @param includeDigits if is true it will include digits in the string
   *
   * @return the randomly generated string
   */
  public String generateRandomString(int length, boolean includeDigits) {

    if (length < 0) {
      throw new IllegalArgumentException("Length must not be negative");
    }

    return generateRandomString(length, includeDigits, true, true);
  }

  /**
   * Takes length as a parameter and returns a random string with the english
   * alphabet
   *
   * @param length        the length of the string as characters
   * @param includeDigits if is true it will include digits in the string
   *
   * @return the randomly generated string
   */
  public String generateRandomString(int length, boolean includeDigits, boolean includeCapital, boolean includeLowercase) {
    if (length < 0) {
      throw new IllegalArgumentException("Length must not be negative");
    }

    if (length == 0) {
      return "";
    }

    StringBuilder builder = new StringBuilder(length);

    String currAlphabet = CAPITAL_ALPHABET + LOWERCASE_ALPHABET;
    boolean hasDigit = false;

    if (includeDigits) {
      currAlphabet = DIGITS + CAPITAL_ALPHABET + LOWERCASE_ALPHABET;

      while (!hasDigit) {
        builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
          builder.append(currAlphabet.charAt(RANDOM.nextInt(currAlphabet.length())));
        }
        hasDigit = hasDigitInString(String.valueOf(builder));
      }
      return String.valueOf(builder);
    }

    for (int i = 0; i < length; i++) {
      builder.append(currAlphabet.charAt(RANDOM.nextInt(currAlphabet.length())));
    }

    return String.valueOf(builder);
  }

  public boolean hasDigitInString(String sequence) {

    if (sequence == null) {
      return false;
    }

    if (sequence.trim().isEmpty()) {
      return false;
    }

    String[] split = sequence.split("");

    for (String character : split) {
      if (Character.isDigit(character.charAt(0))) {
        return true;
      }
    }

    return false;
  }

  public boolean hasOnlyLowercase(String sequence) {

    return hasOnlyOneCase(sequence, false);
  }

  public boolean hasOnlyUppercase(String sequence) {
    return hasOnlyOneCase(sequence, true);
  }

  private boolean hasOnlyOneCase(String sequence, boolean isUppercase) {
    if (sequence == null || sequence.trim().isEmpty()) {
      return false;
    }

    final String[] split = sequence.split("");

    for (String s : split) {

      if (!SPECIAL_CHARACTERS.contains(s)) {
        if (isUppercase) {
          if (s.equals(s.toLowerCase())) {
            return false;
          }
        } else {
          if (s.equals(s.toUpperCase())) {
            return false;
          }
        }
      }
    }

    return true;
  }
}

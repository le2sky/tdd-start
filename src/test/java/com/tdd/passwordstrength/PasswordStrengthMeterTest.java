package com.tdd.passwordstrength;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tdd.passwordstrength.PasswordStrength;
import com.tdd.passwordstrength.PasswordStrengthMeter;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

  private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

  private void assertStrength(String password, PasswordStrength expStr) {
    PasswordStrength result = meter.meter(password);
    assertEquals(expStr, result);
  }

  @Test
  void meetsAlCriteria_Than_Strong() throws Exception {
    assertStrength("ab12!@AB", PasswordStrength.STRONG);
    assertStrength("abc1!Add0", PasswordStrength.STRONG);
  }

  @Test
  void meetsOtherCriteria_except_for_Length_Then_Normal() throws Exception {
    assertStrength("ab12!@A", PasswordStrength.NORMAL);
    assertStrength("Ab12!c", PasswordStrength.NORMAL);
  }

  @Test
  void meetsOtherCriteria_except_for_number_Then_Normal() throws Exception {
    assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
  }

  @Test
  void nullInput_Then_Invalid() throws Exception {
    assertStrength(null, PasswordStrength.INVALID);
  }

  @Test
  void emptyInput_Then_Invalid() throws Exception {
    assertStrength("", PasswordStrength.INVALID);
  }

  @Test
  void meetsOtherCriteria_except_for_Uppercase_Then_Normal() throws Exception {
    assertStrength("ab12!@df", PasswordStrength.NORMAL);
  }

  @Test
  void meetsOnlyLengthCriteria_Then_Weak() throws Exception {
    assertStrength("abdefghi", PasswordStrength.WEAK);
  }

  @Test
  void meetsOnlyUpperCriteria_Then_Weak() throws Exception {
    assertStrength("ABZEF", PasswordStrength.WEAK);
  }

  @Test
  void meetsOnlyNumCriteria_Then_Weak() throws Exception {
    assertStrength("12345", PasswordStrength.WEAK);
  }

  @Test
  void meetsNoCriteria_Then_Weak() throws Exception {
    assertStrength("abc", PasswordStrength.WEAK);
  }
}


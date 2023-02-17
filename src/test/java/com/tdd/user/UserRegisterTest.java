package com.tdd.user;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {


  private UserRegister userRegister;
  private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(stubWeakPasswordChecker);
  }

  @DisplayName("약한 암호면 가입 실패")
  @Test
  public void weekPassword() throws Exception {
    stubWeakPasswordChecker.setWeak(true);

    assertThrows(WeakPasswordException.class, () -> {
      userRegister.register("id", "pw", "email");
    });
  }
}

package com.tdd.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserRegisterMockTest {

  private UserRegister userRegister;
  private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
  private MemoryUserRepository fakeRepositroy = new MemoryUserRepository();
  private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

  @BeforeEach
  void setUp() {
    userRegister = new UserRegister(mockPasswordChecker, fakeRepositroy, mockEmailNotifier);
  }

  @DisplayName("약한 암호면 가입 실패")
  @Test
  public void weakPassword() throws Exception {
    BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

    assertThrows(WeakPasswordException.class, () -> {
      userRegister.register("id", "pw", "email");
    });

  }
}

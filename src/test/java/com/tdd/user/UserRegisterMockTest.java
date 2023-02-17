package com.tdd.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

  @DisplayName("회원 가입시 암호 검사 수행")
  @Test
  public void checkPassword() throws Exception {
    userRegister.register("id", "pw", "email");

    BDDMockito.then(mockPasswordChecker)
        .should()
        .checkPasswordWeak(BDDMockito.anyString());
  }

  @DisplayName("가입하면 메일 전송")
  @Test
  public void whenRegisterThenSendMail() throws Exception {
    userRegister.register("id", "pw", "email@email.com");

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    BDDMockito.then(mockEmailNotifier)
        .should()
        .sendRegisterEmail(captor.capture());

    String realEmail = captor.getValue();
    assertEquals("email@email.com", realEmail);
  }
}

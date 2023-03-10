package com.tdd.autodebit;

import static com.tdd.autodebit.CardValidity.INVALID;
import static com.tdd.autodebit.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoDebitRegister_Stub_Test {

  private AutoDebitRegister register;
  private StubCardNumberValidator stubValidator;
  private StubAutoDebitInfoRepository stubRepository;

  @BeforeEach
  void setUp() {
    stubValidator = new StubCardNumberValidator();
    stubRepository = new StubAutoDebitInfoRepository();
    register = new AutoDebitRegister(stubValidator, stubRepository);
  }

  @Test
  public void invalidCard() throws Exception {
    stubValidator.setInvalidNo("111122223333");

    AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
    RegisterResult result = register.register(req);

    assertEquals(INVALID, result.getValidity());
  }

  @Test
  public void theftCard() throws Exception {
    stubValidator.setTheftNo("1234567890123456");

    AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
    RegisterResult result = register.register(req);

    assertEquals(THEFT, result.getValidity());
  }
}

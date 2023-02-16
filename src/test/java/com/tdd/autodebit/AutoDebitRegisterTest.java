package com.tdd.autodebit;

import static com.tdd.autodebit.CardValidity.THEFT;
import static com.tdd.autodebit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoDebitRegisterTest {

  private AutoDebitRegister register;

  @BeforeEach
  void setUp() {
    CardNumberValidator validator = new CardNumberValidator();
    AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
    register = new AutoDebitRegister(validator, repository);
  }

  @Test
  public void validCard() throws Exception {
    AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
    RegisterResult result = this.register.register(req);
    assertEquals(VALID, result.getValidity());
  }

  @Test
  public void theftCard() throws Exception {
    AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
    RegisterResult result = this.register.register(req);
    assertEquals(THEFT, result.getValidity());
  }
}

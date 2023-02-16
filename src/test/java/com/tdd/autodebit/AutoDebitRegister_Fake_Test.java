package com.tdd.autodebit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoDebitRegister_Fake_Test {

  private AutoDebitRegister register;
  private StubCardNumberValidator stubValidator;
  private MemoryAutoDebitInfoRepository repository;

  @BeforeEach
  void setUp() {
    stubValidator = new StubCardNumberValidator();
    repository = new MemoryAutoDebitInfoRepository();
    register = new AutoDebitRegister(stubValidator, repository);
  }

  @Test
  public void alreadyRegistered_InfoUpdated() throws Exception {
    repository.save(new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

    AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
    RegisterResult result = this.register.register(req);

    AutoDebitInfo saved = repository.findOne("user1");
    assertEquals("123456789012", saved.getCardNumber());
  }

  @Test
  public void notYetRegistered_newInfoRegistered() throws Exception {
    AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
    RegisterResult result = this.register.register(req);

    AutoDebitInfo saved = repository.findOne("user1");
    assertEquals("123456789012", saved.getCardNumber());
  }

}

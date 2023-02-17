package com.tdd.user;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

  private boolean weak;

  public void setWeak(boolean weak) {
    this.weak = weak;
  }
}

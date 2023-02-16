package com.tdd.expirydate;

import java.time.LocalDate;

public class PayData {

  private LocalDate billingDate;
  private int payAccount;
  private LocalDate firstBillingDate;

  private PayData() {
  }

  public PayData(LocalDate billingDate, int payAccount, LocalDate firstBillingDate) {
    this.billingDate = billingDate;
    this.payAccount = payAccount;
    this.firstBillingDate = firstBillingDate;
  }

  public LocalDate getFirstBillingDate() {
    return firstBillingDate;
  }

  public LocalDate getBillingDate() {
    return billingDate;
  }

  public int getPayAccount() {
    return payAccount;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private PayData data = new PayData();

    public Builder billingDate(LocalDate billingDate) {
      data.billingDate = billingDate;
      return this;
    }

    public Builder payAmount(int payAmount) {
      data.payAccount = payAmount;
      return this;
    }

    public Builder firstBillingDate(LocalDate firstBillingDate) {
      data.firstBillingDate = firstBillingDate;
      return this;
    }

    public PayData build() {
      return data;
    }
  }


}

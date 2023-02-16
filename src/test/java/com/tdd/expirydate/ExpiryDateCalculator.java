package com.tdd.expirydate;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

  public LocalDate calculateExpiryDate(PayData payData) {
    int addedMonths = payData.getPayAccount() == 100_000 ? 12 : payData.getPayAccount() / 10_000;
    if (payData.getFirstBillingDate() != null) {
      return expiryDateUsingFirstBillingDate(payData, addedMonths);
    } else {
      return payData.getBillingDate().plusMonths(addedMonths);
    }
  }

  private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
    LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
    final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
    final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
    if (isSameDayOfMonth(candidateExp.getDayOfMonth(), dayOfFirstBilling)) {
      if (dayLenOfCandiMon < dayOfFirstBilling) {
        return candidateExp.withDayOfMonth(dayLenOfCandiMon);
      }
      return candidateExp.withDayOfMonth(dayOfFirstBilling);
    } else {
      return candidateExp;
    }
  }

  private boolean isSameDayOfMonth(int dayOfCandidateExp, int dayOfFirstBilling) {
    return dayOfFirstBilling != dayOfCandidateExp;
  }

  private int lastDayOfMonth(LocalDate candidateExp) {
    return YearMonth.from(candidateExp).lengthOfMonth();
  }
}

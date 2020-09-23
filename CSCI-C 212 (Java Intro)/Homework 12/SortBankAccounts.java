import java.util.Arrays;

public class SortBankAccounts {
  public static void sortAccounts(BankAccount[] accounts) {
    Arrays.sort(accounts, (first, second) -> { return Integer.signum(first.getBalance() - second.getBalance()); });
  }
}

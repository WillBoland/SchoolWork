public class BankAccount {
  private int balance;
  
  public BankAccount() {
    this.balance = 0;
  }
  
  public BankAccount(int balance) {
    this.balance = balance;
  }
  
  public int getBalance() {
    return this.balance;
  }
  
  public String toString() {
    return "Balance: " + this.balance;
  }
}

package Chainblock;

public interface Transaction  {

    int getId();
    TransactionStatus getStatus();
    void setStatus(TransactionStatus status);
    String getTo();
    String getFrom();
    double getAmount();
}

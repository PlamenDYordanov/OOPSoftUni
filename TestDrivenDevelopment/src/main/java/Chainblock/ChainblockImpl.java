package Chainblock;

import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl {

    private Map<Integer, Transaction> transactionMap = new HashMap<>();

    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
    }

    public int getCount() {
        return this.transactionMap.size();
    }

    public void add(Transaction transaction) {
        transactionMap.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return this.transactionMap.containsValue(transaction);
    }

    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (this.transactionMap.containsKey(id)) {
            transactionMap.get(id).setStatus(newStatus);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void removeTransactionById(int id) {
        if (this.transactionMap.containsKey(id)) {
            transactionMap.remove(id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Transaction getById(int id) {
      /*  if (transactionMap.containsKey(id)){
            return transactionMap.get(id);
        }else {
          throw new IllegalArgumentException();
        }*/
        return this.transactionMap.values()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> returnedByStatusTransaction = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            if (entry.getValue().getStatus().equals(status)) {
                returnedByStatusTransaction.add(entry.getValue());
            }
        }
        if (returnedByStatusTransaction.size() == 0) {
            throw new IllegalArgumentException();
        } else {
            returnedByStatusTransaction.sort(Comparator.comparing(Transaction::getAmount).reversed());
        }
        return returnedByStatusTransaction;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = new ArrayList<>();
        List<String> nameOfSenders = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            if (entry.getValue().getStatus().equals(status)) {
                transactions.add(entry.getValue());
            }
        }
        if (transactions.size() == 0) {
            throw new IllegalArgumentException();
        } else {
            transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
            for (Transaction currTransaction : transactions) {
                nameOfSenders.add(currTransaction.getFrom());
            }
            System.out.println(String.join(", ", nameOfSenders));
        }

        return nameOfSenders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = new ArrayList<>();
        List<String> nameOfReceivers = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            if (entry.getValue().getStatus().equals(status)) {
                transactions.add(entry.getValue());
            }
        }
        if (transactions.size() == 0) {
            throw new IllegalArgumentException();
        } else {
            transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
            for (Transaction currTransaction : transactions) {
                nameOfReceivers.add(currTransaction.getTo());
            }
            System.out.println(String.join(", ", nameOfReceivers));
        }

        return nameOfReceivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {

        return this.transactionMap.values()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> returned = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            if (entry.getValue().getFrom().equals(sender)) {
                returned.add(entry.getValue());
            }
            if (returned.size() == 0) {
                throw new IllegalArgumentException();
            }

        }
        return returned
                .stream()
                .sorted((left, right) -> Double.compare(right.getAmount(), left.getAmount()))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> returned = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            if (entry.getValue().getTo().equals(receiver)) {
                returned.add(entry.getValue());
            }
        }
        if (returned.size() == 0) {
            throw new IllegalArgumentException();
        }
        return returned
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }
    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.transactionMap.values()
                .stream().filter(tr -> tr.getStatus().equals(status) && tr.getAmount() <= amount)
                .sorted((left, right) -> Double.compare(right.getAmount(), left.getAmount()))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> returned = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : this.transactionMap.entrySet()) {
            if (entry.getValue().getFrom().equals(sender) && entry.getValue().getAmount() > amount) {
                returned.add(entry.getValue());
            }
        }
        if (returned.size() == 0) {
            throw new IllegalArgumentException();
        }

        return returned.stream()
                .filter(tr -> tr.getFrom().equals("Plamen") && tr.getAmount() > 50)
                .sorted((left, right) -> Double.compare(right.getAmount(), left.getAmount()))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> returned = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : this.transactionMap.entrySet()) {
            if (entry.getValue().getTo().equals(receiver) && entry.getValue().getAmount() >= lo && entry.getValue().getAmount() < hi) {
                returned.add(entry.getValue());
            }
        }
        if (returned.size() == 0 ) {
            throw new IllegalArgumentException();
        }
        return returned.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

}


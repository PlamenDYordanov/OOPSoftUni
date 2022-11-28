package Chainblock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {
    private static final Transaction TRANSACTION_1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Plamen", "Moni", 100);
    private static final Transaction TRANSACTION_2 = new TransactionImpl(2, TransactionStatus.FAILED, "Plamen", "Moni", 101);
    private static final Transaction TRANSACTION_3 = new TransactionImpl(3, TransactionStatus.FAILED, "Ivan", "Maria", 102);

    ChainblockImpl chainBlock;
    List<Transaction> transactions;

    @Before
    public void setUpTest() {
        this.chainBlock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        prepareTransactionForTest();

    }

    private void prepareTransactionForTest() {
        chainBlock.add(TRANSACTION_1);
        transactions.add(TRANSACTION_1);
    }

    public void addTransaction() {
        this.transactions = new ArrayList<>();
        transactions.add(TRANSACTION_3);
        transactions.add(TRANSACTION_2);
        transactions.add(TRANSACTION_1);
    }

    public void addTransactionsToChainBlock() {
        this.chainBlock = new ChainblockImpl();
        chainBlock.add(TRANSACTION_3);
        chainBlock.add(TRANSACTION_2);
        chainBlock.add(TRANSACTION_1);
    }

    @Test //count, add, contains
    public void testAddAndContainsAndCount() {
        chainBlock = new ChainblockImpl();
        assertFalse(chainBlock.contains(TRANSACTION_1));
        chainBlock.add(TRANSACTION_1);
        assertEquals(1, chainBlock.getCount());
        assertTrue(chainBlock.contains(TRANSACTION_1));
        chainBlock.add(TRANSACTION_1);
        assertEquals(1, chainBlock.getCount());
    }

    @Test
    public void testContainsId() {
        ;
        Assert.assertTrue(chainBlock.contains(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusIncorrectIdThrowException() {
        chainBlock.changeTransactionStatus(2, TransactionStatus.FAILED);
    }

    @Test
    public void testChangeTransactionStatusCorrectId() {
        Assert.assertEquals(1, chainBlock.getCount());
        chainBlock.changeTransactionStatus(1, TransactionStatus.UNAUTHORIZED);
        assertEquals(TransactionStatus.UNAUTHORIZED, TRANSACTION_1.getStatus());
        chainBlock.changeTransactionStatus(1, TransactionStatus.SUCCESSFUL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdIncorrectIdThrowException() {
        Assert.assertEquals(1, chainBlock.getCount());
        chainBlock.removeTransactionById(2);
    }

    @Test
    public void testRemoveTransactionByIdCorrectId() {
        Assert.assertEquals(1, chainBlock.getCount());
        chainBlock.removeTransactionById(1);
        assertEquals(0, chainBlock.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdIncorrectIdThrowException() {
        Assert.assertEquals(1, chainBlock.getCount());
        chainBlock.getById(2);
    }

    @Test
    public void testGetByIdCorrectId() {
        Transaction returnedTransaction = transactions.get(0);
        Assert.assertEquals(1, chainBlock.getCount());
        Transaction requireTransaction = chainBlock.getById(returnedTransaction.getId());
        Assert.assertEquals(returnedTransaction, requireTransaction);
        Assert.assertEquals(1, requireTransaction.getId());
        Assert.assertEquals(TransactionStatus.SUCCESSFUL, requireTransaction.getStatus());
        Assert.assertEquals(returnedTransaction.getTo(), requireTransaction.getTo());
        Assert.assertEquals(returnedTransaction.getFrom(), requireTransaction.getFrom());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusIncorrectStatus() {
        Iterable<Transaction> emptyTransaction = chainBlock.getByTransactionStatus(TransactionStatus.ABORTED);
        Assert.assertFalse(emptyTransaction.iterator().hasNext());
    }

    @Test
    public void testGetByTransactionStatus() {
        addTransactionsToChainBlock();
        addTransaction();
        transactions = transactions.stream()
                .filter(tr -> tr.getStatus().equals(TransactionStatus.FAILED))
                .collect(Collectors.toList());
        Iterable<Transaction> result = this.chainBlock.getByTransactionStatus(TransactionStatus.FAILED);
        List<Transaction> returnedTransaction = new ArrayList<>();
        result.forEach(returnedTransaction::add);

        Assert.assertEquals(transactions, returnedTransaction);
        Assert.assertEquals(transactions.size(), returnedTransaction.size());
        returnedTransaction.forEach(tr -> Assert.assertEquals(TransactionStatus.FAILED, tr.getStatus()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusIncorrectStatusThrowException() {
        Iterable<String> emptyTransaction = this.chainBlock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
        Assert.assertFalse(emptyTransaction.iterator().hasNext());
    }

    @Test
    public void testGetAllSendersWithTransactionStatus() {
        addTransaction();
        addTransactionsToChainBlock();
        Iterable<Transaction> getTransaction = chainBlock.getByTransactionStatus(TransactionStatus.FAILED);
        List<Transaction> returnedTransaction = new ArrayList<>();
        getTransaction.forEach(returnedTransaction::add);

        Iterable<String> result = this.chainBlock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
        List<String> nameOfSenders = new ArrayList<>();
        result.forEach(nameOfSenders::add);
        Assert.assertEquals(nameOfSenders.size(), returnedTransaction.size());
        Assert.assertEquals(nameOfSenders.get(0), returnedTransaction.get(0).getFrom());
        Assert.assertEquals(nameOfSenders.get(1), returnedTransaction.get(1).getFrom());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusIncorrectStatusThrowException() {
        Iterable<String> emptyTransaction = this.chainBlock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
        Assert.assertFalse(emptyTransaction.iterator().hasNext());
    }

    @Test
    public void testGetAllReceiversWithTransactionStatus() {
        addTransaction();
        addTransactionsToChainBlock();
        Iterable<Transaction> getTransactions = chainBlock.getByTransactionStatus(TransactionStatus.FAILED);
        List<Transaction> returnedTransaction = new ArrayList<>();
        getTransactions.forEach(returnedTransaction::add);

        Iterable<String> result = this.chainBlock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);
        List<String> nameOfReceivers = new ArrayList<>();
        result.forEach(nameOfReceivers::add);
        Assert.assertEquals(nameOfReceivers.size(), returnedTransaction.size());
        Assert.assertEquals(nameOfReceivers.get(0), returnedTransaction.get(0).getTo());
        Assert.assertEquals(nameOfReceivers.get(1), returnedTransaction.get(1).getTo());
    }

    @Test
    public void testGetOrderedByAmountThenById() {
        addTransactionsToChainBlock();
        addTransaction();
        Iterable<Transaction> result = this.chainBlock.getAllOrderedByAmountDescendingThenById();
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = this.transactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId)).collect(Collectors.toList());
        Assert.assertEquals(expected, returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingIncorrectSenderThrowException() {
        Iterable<Transaction> iterable = chainBlock.getBySenderOrderedByAmountDescending("Kiro");
        Assert.assertFalse(iterable.iterator().hasNext());
    }

    @Test
    public void testGetBySenderOrderedByAmountDescending() {
        addTransaction();
        addTransactionsToChainBlock();
        Iterable<Transaction> result = this.chainBlock.getBySenderOrderedByAmountDescending("Plamen");
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = transactions
                .stream()
                .filter(tr -> tr.getFrom().equals("Plamen"))
                .sorted((left, right) -> Double.compare(right.getAmount(), left.getAmount()))
                .collect(Collectors.toList());
        Assert.assertEquals(expected, returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverOrderedByAmountThenByIdIncorrectReceiversThrowException() {
        Iterable<Transaction> iterable = chainBlock.getByReceiverOrderedByAmountThenById("Dido");
        Assert.assertFalse(iterable.iterator().hasNext());
    }

    @Test
    public void testGetByReceiverOrderedByAmountThenById() {
        addTransactionsToChainBlock();
        addTransaction();
        Iterable<Transaction> result = this.chainBlock.getByReceiverOrderedByAmountThenById("Moni");
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = transactions
                .stream()
                .filter(tr -> tr.getTo().equals("Moni"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Assert.assertEquals(expected, returned);

    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmount() {
        addTransactionsToChainBlock();
        addTransaction();
        Iterable<Transaction> result = this.chainBlock.getByTransactionStatusAndMaximumAmount(TransactionStatus.FAILED, 200);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = transactions
                .stream()
                .filter(tr -> tr.getStatus().equals(TransactionStatus.FAILED) && tr.getAmount() <= 200)
                .sorted((left, right) -> Double.compare(right.getAmount(), left.getAmount()))
                .collect(Collectors.toList());
        Assert.assertEquals(expected, returned);
    }

    @Test
    public void testGetByTransactionStatusAndMinimumAmount() {
        addTransactionsToChainBlock();
        addTransaction();
        Iterable<Transaction> result = this.chainBlock.getBySenderAndMinimumAmountDescending("Plamen", 50);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = transactions
                .stream()
                .filter(tr -> tr.getFrom().equals("Plamen") && tr.getAmount() > 50)
                .sorted((left, right) -> Double.compare(right.getAmount(), left.getAmount()))
                .collect(Collectors.toList());
        Assert.assertEquals(expected, returned);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusAndMinimumAmountIncorrectSender() {
        Iterable<Transaction> iterable = chainBlock.getBySenderAndMinimumAmountDescending("Kiro", 20);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeIncorrectReceiverThrowException(){
        Iterable<Transaction> iterable = chainBlock.getByReceiverAndAmountRange("Kiro", 10 ,20);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test
    public void testGetByReceiverAndAmountRange() {
        addTransaction();
        addTransactionsToChainBlock();
        Iterable<Transaction> result = chainBlock.getByReceiverAndAmountRange("Moni",20, 103);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = transactions
                .stream()
                .filter(tr -> tr.getTo().equals("Moni") && tr.getAmount() >= 20 && tr.getAmount() < 103 )
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Assert.assertEquals(expected, returned);
    }

}
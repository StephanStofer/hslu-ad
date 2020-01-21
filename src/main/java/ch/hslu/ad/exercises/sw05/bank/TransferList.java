/**
 * TransferList-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-03-22
 */
package ch.hslu.ad.exercises.sw05.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class TransferList {

    private static final Logger LOG = LogManager.getLogger(TransferList.class);

    private final int numberOfAccounts = 2;
    private final int initialAmount = 300_000;
    private final int transferAmount = 100_000;

    private ArrayList<BankAccount> sourceList = new ArrayList<>(numberOfAccounts);
    private ArrayList<BankAccount> targetList = new ArrayList<>(numberOfAccounts);
    private final Thread[] threads = new Thread[numberOfAccounts * 2];


    public TransferList() {
        for (int i = 0; i < numberOfAccounts; i++){
            sourceList.add(new BankAccount(initialAmount));
            targetList.add(new BankAccount());
        }
    }

    public void printBalances(){
        for (BankAccount ba: sourceList
             ) {
            LOG.info("Source-Account {} has balance {}",ba.toString(),ba.getBalance());
        }
        for (BankAccount ba: targetList
             ) {
            LOG.info("Target-Account {} has balance {}",ba.toString(),ba.getBalance());
        }
    }

    public void sendAmounts(){
        for (int i=0;i<numberOfAccounts;i++){
            threads[i] = new Thread(new AccountTask( sourceList.get(i), targetList.get(i), transferAmount));
                LOG.info("– – – – – – – – – – – – Thread {} started– – – – – – – – – – – – – – – ",threads[i].getId());

                LOG.info("Thread {} returned; Source Balance: {} and Target Balance: {}",threads[i].getId(),sourceList.get(i).getBalance(),targetList.get(i).getBalance());

            threads[i + numberOfAccounts] = new Thread(new AccountTask( targetList.get(i), sourceList.get(i), transferAmount));
            LOG.info("– – – – – – – – – – – – Thread {} started– – – – – – – – – – – – – – – ",threads[i].getId());


                LOG.info("Thread {} returned; Target Balance: {} and Source Balance: {}",threads[i].getId(),targetList.get(i).getBalance(),sourceList.get(i).getBalance());

        }
        for (final Thread thread : threads){
            thread.start();
        }
        for (final Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TransferList tl = new TransferList();

        tl.sendAmounts();

    }
}
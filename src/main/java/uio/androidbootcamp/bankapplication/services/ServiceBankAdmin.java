package uio.androidbootcamp.bankapplication.services;

import uio.androidbootcamp.bankapplication.entities.*;
import uio.androidbootcamp.bankapplication.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceBankAdmin {

    private List<Client> clients;
    private List<CurrentAccount> currentAccounts;
    private List<SavingsAccount> savingsAccounts;

    public ServiceBankAdmin(){
        this.clients = new ArrayList<>();
        this.currentAccounts = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
    }

    public Client createClient(String name, String lastName, String id) {
        Client newClient = new Client(name, lastName, id);
        this.clients.add(newClient);
        return newClient;
    }


    public AccountBank createCurrentAccount(String id) {
        CurrentAccount currentAccount = new CurrentAccount(id);
        currentAccounts.add(currentAccount);
        return currentAccount;
    }

    public AccountBank createSavingAccount(String id){
        SavingsAccount savingsAccount = new SavingsAccount(id);
        savingsAccounts.add(savingsAccount);
        return savingsAccount;
    }

    public Client addAccountToClient(Client client, AccountBank accountBank) {
        Client clientClone = client.clone();
        AccountBank accountBankClone = null;

        if(accountBank instanceof CurrentAccount){
            accountBankClone = new CurrentAccount(accountBank.getId());
        }
        else if(accountBank instanceof SavingsAccount){
            accountBankClone = new SavingsAccount(accountBank.getId());
        }

        clientClone.addOneAccount(accountBankClone);
        deleteAccount(accountBank);
        getClients().remove(client);
        getClients().add(clientClone);

        return clientClone;
    }

    public CurrentAccount depositToCurrentAccount(double depositQuantity, CurrentAccount currentAccount) throws NegativeValuesException {
        CurrentAccount currentAccountClone = currentAccount.clone();
        currentAccountClone.deposit(depositQuantity);
        return currentAccountClone;
    }

    public CurrentAccount withdrawFromCurrentAccount(double withdrawQuantity, CurrentAccount currentAccount) throws ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException {
        CurrentAccount currentAccountClone = currentAccount.clone();
        currentAccountClone.withdraw(withdrawQuantity);
        return currentAccountClone;
    }

    public SavingsAccount depositToSavingAccount(double depositQuantity, SavingsAccount savingsAccount) throws NegativeValuesException {
        SavingsAccount savingsAccountClone = savingsAccount.clone();
        savingsAccountClone.deposit(depositQuantity);
        return savingsAccountClone;
    }

    public SavingsAccount withdrawFromSavingAccount(double withdrawQuantity, SavingsAccount savingAccount) throws ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
        SavingsAccount savingAccountClone = savingAccount.clone();
        savingAccountClone.withdraw(withdrawQuantity);
        return savingAccountClone;
    }

    public TransferAccounts transferBetweenAccounts(AccountBank accountBankTransmitter, AccountBank accountBankReceiver, double transferQuantity) throws ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
        AccountBank accountBankTransmitterClone = accountBankTransmitter.clone();
        AccountBank accountBankReceiverClone = accountBankReceiver.clone();
        accountBankTransmitterClone.withdraw(transferQuantity);
        accountBankReceiverClone.deposit(transferQuantity);
        return new TransferAccounts(accountBankTransmitterClone, accountBankReceiverClone);
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<CurrentAccount> getCurrentAccounts() {
        return this.currentAccounts;
    }

    public List<SavingsAccount> getSavingAccounts() {
        return savingsAccounts;
    }

    public Client searchClient(String id) throws ClientNotFoundException {
        int index = getClients().size()-1;
        Client client = null;
        while(index > -1){
            if(getClients().get(index).getId().equals(id)){
                client = getClients().get(index);
                break;
            }
            index -=index;
        }

        if(client == null) throw new ClientNotFoundException();

        return client;
    }


    public AccountBank searchAccountBank(String id) throws AccountBankNotFoundException{
        int indexCurrentAccount = getCurrentAccounts().size()-1;
        int indexSavingAccount = getSavingAccounts().size()-1;
        AccountBank accountBank = null;
        while(indexCurrentAccount > -1){
            if(getCurrentAccounts().get(indexCurrentAccount).getId().equals(id)){
                accountBank = getCurrentAccounts().get(indexCurrentAccount);
                break;
            }
            indexCurrentAccount =indexCurrentAccount-1;
        }

        while(indexSavingAccount > -1){
            if(getSavingAccounts().get(indexSavingAccount).getId().equals(id)){
                accountBank = getSavingAccounts().get(indexSavingAccount);
                break;
            }
            indexSavingAccount =indexSavingAccount-1;
        }

        if(accountBank == null) throw new AccountBankNotFoundException();

        return accountBank;
    }

    private void deleteAccount(AccountBank accountBank){
        if(accountBank instanceof CurrentAccount){
            getCurrentAccounts().remove(accountBank);
        }

        if(accountBank instanceof  SavingsAccount){
            getSavingAccounts().remove(accountBank);
        }

    }

    public AccountBank searchAccountOfClientGivenAccountId(String idClient, String idAccount) throws ClientNotFoundException, AccountBankNotFoundException {
        Client client = searchClient(idClient);
        int indexBankAccount = client.getAccountsBank().size()-1;
        AccountBank accountBank = null;
        while(indexBankAccount > -1){
            if(client.getAccountsBank().get(indexBankAccount).getId().equals(idAccount)){
                accountBank = client.getAccountsBank().get(indexBankAccount);
                break;
            }
            indexBankAccount =indexBankAccount-1;
        }

        if(accountBank == null) throw new AccountBankNotFoundException();

        return accountBank;
    }
}

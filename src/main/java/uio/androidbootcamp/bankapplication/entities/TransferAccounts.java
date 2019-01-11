package uio.androidbootcamp.bankapplication.entities;

public class TransferAccounts {
    public AccountBank accountTransmitter;
    public AccountBank accountReceiver;

    public TransferAccounts(AccountBank accountBankTransmitter, AccountBank accountBankReceiver) {
        this.accountTransmitter = accountBankTransmitter;
        this.accountReceiver = accountBankReceiver;
    }
}

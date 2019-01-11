package uio.androidbootcamp.bankapplication.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private List<AccountBank> accountsBank;
    private String name;
    private String lastName;
    private String id;

    public Client(String name, String lastName, String id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.accountsBank = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }


    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getId() {
        return this.id;
    }

    public void addOneAccount(AccountBank account) {
        this.accountsBank.add(account);
    }

    public List<AccountBank> getAccountsBank() {
        return this.accountsBank;
    }

    public Client clone(){
        Client clientClone = new Client(this.name, this.lastName, this.id);
        clientClone.accountsBank = this.accountsBank;
        return clientClone;
    }
}

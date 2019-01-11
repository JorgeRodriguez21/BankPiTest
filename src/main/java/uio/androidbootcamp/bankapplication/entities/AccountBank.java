package uio.androidbootcamp.bankapplication.entities;

import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper2000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

import java.util.Objects;

abstract public class AccountBank {

    protected double interest;
    protected double balance;
    protected String status;
    protected String id;


    public abstract void deposit(double depositQuantity) throws NegativeValuesException;

    public abstract void withdraw(double withdrawQuantity) throws NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception, ValueUpper1000Exception;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountBank)) return false;
        AccountBank that = (AccountBank) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId(){
        return this.id;
    }


    public double getBalance(){
        return this.balance;
    }

    public abstract AccountBank clone();

}

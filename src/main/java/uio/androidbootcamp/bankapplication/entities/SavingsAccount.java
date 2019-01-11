package uio.androidbootcamp.bankapplication.entities;

import uio.androidbootcamp.bankapplication.exceptions.NegativeValuesException;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpper1000Exception;
import uio.androidbootcamp.bankapplication.exceptions.ValueUpperBalanceException;

public class SavingsAccount extends AccountBank{

    public SavingsAccount(String id){
        this.interest = 0.0001;
        this.status = "Activa";
        this.id = id;
    }

    public void deposit(double depositQuantity) throws NegativeValuesException {
        if(depositQuantity >=0){
            this.balance += depositQuantity;
        }
        else{
            throw  new NegativeValuesException();
        }
    }

    public void withdraw(double withdrawQuantity) throws NegativeValuesException, ValueUpperBalanceException, ValueUpper1000Exception {
        if(withdrawQuantity < 0.00){
            throw new NegativeValuesException();
        }
        else if(withdrawQuantity > this.balance){
            throw new ValueUpperBalanceException();
        }
        else if (withdrawQuantity > 1000.00){
            throw new ValueUpper1000Exception();
        }
        else{
            this.balance -= withdrawQuantity;
        }

    }

    public double getInterest() {
        return this.interest;
        // o return 0.0001
    }

    public String getStatus() {
        return status;
    }

    public SavingsAccount clone(){
        SavingsAccount savingsAccountClone = new SavingsAccount(this.id);

        savingsAccountClone.balance = this.balance;
        savingsAccountClone.interest = this.interest;
        savingsAccountClone.status = this.status;
        return savingsAccountClone;
    }



}

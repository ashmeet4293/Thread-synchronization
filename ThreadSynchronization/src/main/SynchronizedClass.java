package main;

import java.util.Scanner;

 class BankAccount{
    Scanner kb=new Scanner(System.in);
    double balance=1000,withdraw;
    public void withDraw(double withdraw){
        try{
            if(withdraw>balance){
                throw new Exception();
            }
            balance=balance-withdraw;
            System.out.println("Total balance after withdrawl : "+balance);
        }catch (Exception ex ){
            System.out.println("Insufficient Balance");
        }
    }
}
class Client extends Thread{
    Scanner kb=new Scanner(System.in);
    String name;
    double withdraw;
    BankAccount account;
    Client (BankAccount account){ //paramaterized constructor
        this.account=account;
    }
    public void run(){
        synchronized(account){
            System.out.println("Enter name : ");
            name=kb.next();
             System.out.println("Enter withdraw amount : ");
            withdraw=kb.nextDouble();
            account.withDraw(withdraw);
            System.out.println("Account received by : "+name);
        }
    }
}
public class SynchronizedClass{
    public static void main(String []args){
        BankAccount account=new BankAccount();
        Client t1=new Client(account);
        Client t2=new Client(account);
        t1.start();
        t2.start();
    }
}
package com.myhome.jdk5.thread.concurrence;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MonMain {

	
	public MonMain() {
		Thread a =   new Thread (new WithdrawMoney());
		Thread b=	    new Thread ( new WithdrawMoney());
		
		a.start();
	b.start();
	}
	
    final BankAccount account = new BankAccount();
	public static void main (String[] args)
	{
	    // Le compte bancaire partagé
	 Set<String> to = new HashSet<String>();
	 to.add("gee@gmail.com");
	 to.add("dedee@gmail.com");
//	 InternetAddress[] aa =null;
//	  try {
//		  aa =InternetAddress
//			.parse("", false);
//		  
//		  
//	} catch (AddressException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		System.out.println("".length());
	    // Alice et Bob veulent retirer 200 euros
	   
	}
	
	
	public static String toEmailAddressList(Set<String> to) {
		to.remove(null);
		Iterator<String> iter = to.iterator();
		StringBuffer str = new StringBuffer();
		int i = 0;
		while (iter.hasNext()) {
			if (i > 0) {
				str.append(", ");
			}
			str.append(iter.next());
			i++;
		}
		return str.toString();
	}

		
	 
    class WithdrawMoney implements Runnable
    {
        public void run()
        {
        	int i=0;
        	while(i<1000){
            account.increment();
            System.out.println ("Il a " + account.getBalance() + " euros sur le compte");
            i++;
        	}
        }
    }
 
	public class BankAccount
	{
	    private int balance = 0;
	 
	    public int getBalance()
	    {
	        return balance;
	    }
	 
	    public void increment ()
	    {
	    	balance++;
	    }
	}
}

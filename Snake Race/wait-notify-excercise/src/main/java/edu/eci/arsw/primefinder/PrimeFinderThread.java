package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PrimeFinderThread extends Thread {


	int a, b;

	private List<Integer> primes;
	
	private boolean p;

	public PrimeFinderThread(int a, int b) {
		super();
		this.primes = new LinkedList<>();
		this.a = a;
		this.b = b;
		this.p = false;
	}

	@Override
	public void run() {
		try {
			for(int i=a; i<b; i++) {
				if(isPrime(i)) {
					primes.add(i);
					System.out.println(i);
				}
				synchronized (this) {
					while(p) {
						wait();
					}
				}
			}
		}catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	public void detener()  {
		synchronized (this) {
			p = true;
			notifyAll();	
		}
		
	}

	void notificar() {
		synchronized (this) {
			p = false;
			notifyAll();	
		}
	}

	boolean isPrime(int n) {
		boolean ans;
		if (n > 2) {
			ans = n % 2 != 0;
			for (int i = 3; ans && i * i <= n; i += 2) {
				ans = n % i != 0;
			}
		} else {
			ans = n == 2;
		}
		return ans;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

}

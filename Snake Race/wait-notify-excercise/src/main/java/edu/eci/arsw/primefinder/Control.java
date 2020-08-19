/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.TimerTask;
import javax.swing.*;

/**
 *
 */
public class Control extends Thread{
	
	private final static int NTHREADS = 3;
	private final static int MAXVALUE = 20000000;
	public final static int TMILISECONDS = 5000;
	

	private final int NDATA = MAXVALUE / NTHREADS;

	private PrimeFinderThread pft[];
	
	private boolean detenidos;

	private Control() {
		super();
		
		this.pft = new PrimeFinderThread[NTHREADS];

		int i;
		for (i = 0; i < NTHREADS - 1; i++) {
			PrimeFinderThread elem = new PrimeFinderThread(i * NDATA, (i + 1) * NDATA);
			pft[i] = elem;
		}
		pft[i] = new PrimeFinderThread(i * NDATA, MAXVALUE + 1);
	}

	public static Control newControl() {
		return new Control();
	}

	@Override
	public void run() {
		//TimerThread tm = new TimerThread();
		for (int i = 0; i < NTHREADS; i++) {
			pft[i].start();
		}
		
		Timer timer = new Timer(TMILISECONDS,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<NTHREADS; i++) {
					pft[i].detener();
				}
				detenidos = true;
				while(detenidos) {
					reiniciar();
				}
			}
		});
		timer.start();
	}
	
	private void reiniciar() {
		Scanner a = new Scanner(System.in);
		String tecla = a.nextLine();
		if(tecla.isEmpty()) {
			detenidos = false;
			for(int i=0; i<NTHREADS; i++) {
				pft[i].notificar();
			}
		}
	}

	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        Thread t1 = new Thread(new CountThread(0, 99));
        t1.start();
        Thread t2 = new Thread(new CountThread(100, 199));
        t2.start();
        Thread t3 = new Thread(new CountThread(200, 299));
        t3.start();
    }
    
}

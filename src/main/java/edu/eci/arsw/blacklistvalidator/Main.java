/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.LinkedList;
import java.util.List;

import edu.eci.arsw.threads.BlackListThread;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]){
        HostBlackListsValidator hblv=new HostBlackListsValidator();
        LinkedList<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55",1);
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        System.out.println(BlackListThread.total);
    }
    
}

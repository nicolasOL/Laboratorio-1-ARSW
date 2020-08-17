package edu.eci.arsw.threads;

import java.util.LinkedList;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class BlackListThread extends Thread{
	
	public static int total = 0;
	public static LinkedList<Integer> blackListOcurrences=new LinkedList<>();
	
	private static final int BLACK_LIST_ALARM_COUNT=5;
		
	public int ocurrencesCount=0;
	
	HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    
    int checkedListsCount=0;
    int servers = skds.getRegisteredServersCount();
    
    String ipaddress;
    int numberT;
    int inicial;
    	
    public BlackListThread(String ipaddress, int numberThreads, int inicial) {
    	this.ipaddress = ipaddress;
    	this.numberT = numberThreads;
    	this.inicial = inicial;
    }
	
	public void run() {
        
		for (int i=numberT;i<=inicial;i++){
            checkedListsCount++;
            
            if (skds.isInBlackListServer(i, ipaddress)){  
                ocurrencesCount+=1;
                blackListOcurrences.add(i);
            }
        }
		total+=ocurrencesCount;
	}


	public int getOcurrencesCount() {
		return ocurrencesCount;
	}


	public void setOcurrencesCount(int ocurrencesCount) {
		this.ocurrencesCount = ocurrencesCount;
	}
	
	public LinkedList<Integer> getblackListOcurrences(){
		return blackListOcurrences;
	}
	
	

}

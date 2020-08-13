package edu.eci.arsw.threads;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class BlackListThread implements Runnable{
	
	public static int total = 0;
	
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
	
	
	@Override
	public void run() {
        
		for (int i=numberT-inicial;i<numberT && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            
            if (skds.isInBlackListServer(i, ipaddress)){                
                ocurrencesCount++;
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
	
	

}

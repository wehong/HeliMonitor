/**
 * 
 * @author wehong
 * 
 **/

import java.io.*;
import java.net.*;
import java.util.*;

public class Comm extends Thread {
	DatagramSocket ds;
	DatagramPacket packet;
	int port;
	int timeout;
	byte[] buf;
	String bufstr;
	private volatile boolean loopflag;
	
	Monitor mon;
	
	Comm(Monitor mon, int port, int timeout, String filename) {
		this.mon = mon;
		this.port = port;
		this.timeout = timeout;
		try {
			ds = new DatagramSocket(port);
			//packet = new DatagramPacket(buf, buf.length);
			ds.setSoTimeout(timeout);
			buf = new byte[255];
			for(int i=0;i<255;i++) {
				buf[i]=0;
			}
			loopflag = true;
		}
		catch(SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			while(loopflag) {
				try {
					packet = null;
					packet = new DatagramPacket(buf, buf.length);
					if(ds!=null) ds.receive(packet);
					
					bufstr = new String(buf);
					
					if(bufstr.charAt(0) == 'a') {
						parseToAngle(bufstr.substring(1));
					}
					else if(bufstr.charAt(0) == 'g') {
						parseToGps(bufstr.substring(1));
					}
					else if(bufstr.charAt(0)=='t') {
						parseToTime(bufstr.substring(1));
					}
					else if(bufstr.charAt(0)=='p') {
						parseToPitch(bufstr.substring(1,6));
					}
					else if(bufstr.charAt(0)=='r') {
						parseToRoll(bufstr.substring(1,6));
					}
					else {
						// raw data
						if(bufstr.substring(0, 6).compareTo("$GPADC")==0) {
							parseToAdc(bufstr);
						}
						else if(bufstr.substring(0, 6).compareTo("$GPPPM")==0) {
							parseToPpm(bufstr);
						}
						else mon.updateRawdata(bufstr);
					}
				
					for(int i=0; i<255; i++) {
						buf[i]=0;
					}
				}
				catch(InterruptedIOException e) {
					System.out.println("waiting");
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// methods -----------------------------------------
	void controlThread(boolean flag) {
		loopflag = flag;
	}
	
	void reconnect() {
		try {
			controlThread(false);
			sleep(2000);
			ds.close();
			ds = null;
			ds = new DatagramSocket(port);
			ds.setSoTimeout(timeout);
			controlThread(true);
		}
		catch(SocketException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void parseToAngle(String readStr) {
		int c = 0;
		float first = (float)0.0;
		float second = (float)0.0;
		float third = (float)0.0;
		
		StringTokenizer st = new StringTokenizer(readStr, "aa", false);
		
		try {
			while(st.hasMoreTokens()) {
				if(c==0) {
					first=Float.parseFloat(st.nextToken());
				}
				else if(c==1) {
					second=Float.parseFloat(st.nextToken());
				}
				else if(c==2) {
					third=Float.parseFloat(st.nextToken());
				}
				c++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//return;
		}
		mon.updateAngle(-first, -second, -third);
	}
	
	void parseToGps(String readStr) {
		int c = 0;
		float first = (float)0.0;
		float second = (float)0.0;
		float third = (float)0.0;
		
		StringTokenizer st = new StringTokenizer(readStr, "aa", false);
		
		try {
			while(st.hasMoreTokens()) {
				if(c==0) {
					first=Float.parseFloat(st.nextToken());
				}
				else if(c==1) {
					second=Float.parseFloat(st.nextToken());
				}
				else if(c==2) {
					third=Float.parseFloat(st.nextToken());
				}
				c++;
			}
		}
		catch(Exception e) {
			return;
		}
		mon.updateGps(first, second, third);
	}

	void parseToTime(String readStr) {
		long sec = 0;
		long nsec = 0;
		int phase = 0;
		String s;
		
		StringTokenizer st = new StringTokenizer(readStr, "tt", false);
		
		try {
			while(st.hasMoreTokens()) {
				if(phase==0) {
					sec = Long.parseLong(st.nextToken());
				}
				else if(phase==1) {
					nsec = Long.parseLong(st.nextToken());
				}
				else {
					s = st.nextToken();
				}
				phase++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//return;
		}
		mon.updateTime(sec, nsec);
	}
	
	void parseToPitch(String readStr) {
		int pp;
		//StringTokenizer st = new StringTokenizer(readStr, "pp", false);
		
		/*try {
			while(st.hasMoreTokens()) {
				pp = Integer.parseInt(st.nextToken());
				mon.updatePitch(pp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
		
		pp = Integer.parseInt(readStr);
		mon.updatePitch(pp);
	}
	
	void parseToRoll(String readStr) {
		int rr;
		/*StringTokenizer st = new StringTokenizer(readStr, "rr", false);
		
		try {
			while(st.hasMoreTokens()) {
				rr = Integer.parseInt(readStr);
				mon.updateRoll(rr);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
		
		rr = Integer.parseInt(readStr);
		mon.updateRoll(rr);
	}

	void parseToAdc(String readStr) {	
			int c = 0;
		
			/*long lo4 = (long)0.0;
			long lo5 = (long)0.0;
			long lo6 = (long)0.0;
			long lo8 = (long)0.0;*/
			String s4 = "", s5 = "", s6 = "", s8 = "";
	
			StringTokenizer st = new StringTokenizer(readStr, ",", false);
	
			try {
				while(st.hasMoreTokens()) {
					if(c==0) {
						st.nextToken();
					}
					else if(c==1) {
						st.nextToken();
					}
					else if(c==2) {
						st.nextToken();
					}
					else if(c==3) {
						st.nextToken();
					}
					else if(c==4) {
						//lo4 = Long.parseLong(st.nextToken(), 16);
						s4 = st.nextToken();
					}
					else if(c==5) {
						//lo5 = Long.parseLong(st.nextToken(), 16);
						s5 = st.nextToken();
					}
					else if(c==6) {
						//lo6 = Long.parseLong(st.nextToken(), 16);
						s6 = st.nextToken();
					}
					else if(c==7) {
						st.nextToken();
					}
					else if(c==8) {
						//lo8 = Long.parseLong(st.nextToken().substring(0, 4), 16);
						s8 = st.nextToken().substring(0, 4);
					}
					c++;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				//return;
			}
			//mon.updateAdc(lo5, lo4, lo6, lo8);
			mon.updateAdc(s5, s4, s6, s8);
		}
		
	void parseToPpm(String readStr) {
		int phase = 0;
		
		long ch1 =0 , ch2 = 0, ch3 = 0, ch4 = 0, ch5 = 0, ch6 = 0, ch7 = 0, ch8 = 0, ch9 = 0; 
		
		StringTokenizer st = new StringTokenizer(readStr, ",", false);
		
		try {
			while(st.hasMoreTokens()) {
				if(phase==0) {
					st.nextToken();
				}
				else if(phase==1) {
					ch1 = Long.parseLong(st.nextToken(),16);
				}
				else if(phase==2) {
					ch2 = Long.parseLong(st.nextToken(),16);
				}
				else if(phase==3) {
					ch3 = Long.parseLong(st.nextToken(),16);
				}
				else if(phase==4) {
					ch4 = Long.parseLong(st.nextToken(), 16);
				}
				else if(phase==5) {
					ch5 = Long.parseLong(st.nextToken(), 16);
				}
				else if(phase==6) {
					ch6 = Long.parseLong(st.nextToken(), 16);
				}
				else if(phase==7) {
					ch7 = Long.parseLong(st.nextToken(), 16);
				}
				else if(phase==8) {
					ch8 = Long.parseLong(st.nextToken(), 16);
				}
				else if(phase==9) {
					ch9 = Long.parseLong(st.nextToken().substring(0, 4), 16);
				}
				phase++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//return;
		}
		mon.updatePpm(ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9);
	}
}
/**
 * 
 * @author wehong
 * 
 **/

import java.io.*;

public class Monitor {

	private View view;
	private Comm comm;
	
	private volatile boolean logflag;
	private volatile boolean angleflag;
	private volatile boolean gpsflag;
	private volatile boolean timeflag;
	private volatile boolean gpadcflag;
	private volatile boolean gpppmflag;
	private volatile boolean pitchflag;
	private volatile boolean rollflag;
	
	private volatile String filename;
	PrintStream ps;
	
	// constructor
	Monitor() {
		logflag = false;
		
		view = new View(this, "Heli Monitor");
		view.init();
		
		try {
			if(filename!=null)	ps = new PrintStream(new FileOutputStream(filename));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		comm = new Comm(this, 12367, 1000, null);
		comm.start();
	}


	// methods -----------------------------------
	void updateAngle(float f1, float f2, float f3) {
		String output = "Angle," + f1 + "," + f2 + "," + f3;
		view.updateAngle(f1, f2);
		if(logflag && angleflag) {
			ps.println(output);
		}
	}
	
	void updateGps(float f1, float f2, float f3) {
		String output = "Gps," + f1 + "," + f2 + "," + f3;
		view.updateGps(f1, f2, f3);
		if(logflag && gpsflag) {
			ps.println(output);
		}
	}

	void updateTime(long s, long ns) {
		String output = "ElapsedTime," + s + "," + ns;
		view.updateTime(s, ns);
		if(logflag && timeflag) {
			ps.println(output);
		}
	}
	
	void updatePitch(int p) {
		String output = "P," + p;
		if(logflag && pitchflag) {
			ps.println(output);
		}
	}

	void updateRoll(int r) {
		String output = "R," + r;
		if(logflag && rollflag) {
			ps.println(output);
		}
	}

	/*void updateAdc(double gx, double ax, double gy, double ay) {
		String output = "ADC," + gx +"," + ax + "," + gy + "," + ay;
		view.updateAdc(gx, ax, gy, ay);
		if(logflag && gpadcflag) {
			ps.println(output);
		}
	}*/
	
	void updateAdc(String gx, String ax, String gy, String ay) {
			String output = "ADC," + gx +"," + ax + "," + gy + "," + ay;
			view.updateAdc(gx, ax, gy, ay);
			if(logflag && gpadcflag) {
				ps.println(output);
			}
	}
	
	void updatePpm(long ch1, long ch2, long ch3, long ch4, long ch5, long ch6, long ch7, long ch8, long ch9) {
		String output = "PPM," + ch1 + "," + ch2 + "," + ch3 +"," + "," +ch4 + "," + ch5 + "," + ch6 + "," + ch7 + "," + ch8 + "," + ch9;
		view.updatePpm(ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9);
		if(logflag && gpppmflag) {
			ps.println(output);
		}
	}
	
	void updateRawdata(String rawdata) {
	}
	
	void terminateMonitor() {
		comm.controlThread(false);
		System.exit(0);
	}

	void changeLogflag(boolean b) {
		logflag = b;
	}
	
	void changeLogOptions(boolean angle, boolean gps, boolean time, boolean gpadc, boolean gpppm, boolean pitch, boolean roll) {
		angleflag = angle;
		gpsflag = gps;
		timeflag = time;
		gpadcflag = gpadc;
		gpppmflag = gpppm;
		pitchflag = pitch;
		rollflag = roll;
	}
	
	void changeFilename(String s) throws Exception {
		filename = s;
		ps = null;
		ps = new PrintStream(new FileOutputStream(filename));
	}
	
	void reconnect() {
		comm.reconnect();
	}
	
	// main ----------------------------------------
	public static void main(String[] args) {
		Monitor mon = new Monitor(); 
	}
}

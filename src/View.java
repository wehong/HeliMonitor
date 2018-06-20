/**
 * 
 * @author wehong
 * 
 **/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class View extends JFrame implements ActionListener {

	Monitor mon;
	
	JPanel jpTop;
	JPanel jpSensor;
	JPanel jpPitch, jpRoll, jpGps;
	JPanel jpPpm;
	JPanel jpLeft;
	JPanel jpAttitude;
	JPanel jpControl;
	JPanel jpControlCenter;
	JPanel jpGraph;
	JPanel jpGraphPitch;
	JPanel jpGraphRoll;
	JPanel jpLog;
	JPanel jpLogOption;
	JPanel jpCenter;
	JPanel jpTime;
	
	TitledBorder tbSensor;
	TitledBorder tbPpm;
	TitledBorder tbAttitude;
	TitledBorder tbControl;
	TitledBorder tbLog;
	TitledBorder tbLogOption;
	TitledBorder tbGraph;
	
	TitledBorder tbTime;
	
	JLabel jlPitch;
	JLabel jlPitchAngle;
	JLabel jlRollAngle;
	JLabel jlPitchGyro;
	JLabel jlRoll;
	JLabel jlRollGyro;
	JLabel jlPitchAcc;
	JLabel jlRollAcc;
	JLabel jlGps;
	JLabel jlGpsX;
	JLabel jlGpsY;
	JLabel jlGpsZ;
	JLabel jlLogFilename;
	JLabel jlLogStatus;
	JLabel jlPpm1;
	JLabel jlPpm2;
	JLabel jlPpm3;
	JLabel jlPpm4;
	JLabel jlPpm5;
	JLabel jlPpm6;
	JLabel jlPpm7;
	JLabel jlPpm8;
	JLabel jlPpm9;
	JLabel jlGraphPitch;
	JLabel jlGraphRoll;
	JLabel jlControlCh5;
	JLabel jlTimeSec;
	JLabel jlTimeNSec;
	
	JTextField jtfPitchAngle;
	JTextField jtfRollAngle;
	JTextField jtfPitchGyro;
	JTextField jtfRollGyro;
	JTextField jtfPitchAcc;
	JTextField jtfRollAcc;
	JTextField jtfGpsX;
	JTextField jtfGpsY;
	JTextField jtfGpsZ;
	JTextField jtfLogFilename;
	JTextField jtfLogStatus;
	JTextField jtfPpm1;
	JTextField jtfPpm2;
	JTextField jtfPpm3;
	JTextField jtfPpm4;
	JTextField jtfPpm5;
	JTextField jtfPpm6;
	JTextField jtfPpm7;
	JTextField jtfPpm8;
	JTextField jtfPpm9;
	JTextField jtfTimeSec;
	JTextField jtfTimeNSec;
	
	JLabel jlBlankP1, jlBlankP2, jlBlankP3;
	JLabel jlBlankR1, jlBlankR2, jlBlankR3;
	JLabel jlBlankG1, jlBlankG2, jlBlankG3;
	JLabel jlBlankL1, jlBlankL2, jlBlankL3, jlBlankL4;
	JLabel jlBlankPp1, jlBlankPp2, jlBlankPp3 , jlBlankPp4, jlBlankPp5, jlBlankPp6, jlBlankPp7, jlBlankPp8;
	
	Checkbox cbAngle;
	Checkbox cbGps;
	Checkbox cbTime;
	Checkbox cbGpadc;
	Checkbox cbGpppm;
	Checkbox cbPitch;
	Checkbox cbRoll;
	
	JButton jbStart;
	JButton jbStop;
	
	AttCanvas acAtt;
	
	GraphCanvas gcPitch, gcRoll;
	
	LControlCanvas lccLeft;
	RControlCanvas rccRight;
	
	View(Monitor m, String s) {
		super(s);
		
		mon = m;
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mon.terminateMonitor();
			}
		});
		
		// sensor
		jpSensor = new JPanel();
		jpSensor.setLayout(new GridLayout(3,1));
		
		tbSensor = new TitledBorder("");
		tbSensor.setTitle(" Sensors ");
		tbSensor.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbSensor.setTitleJustification(TitledBorder.CENTER);
		jpSensor.setBorder(tbSensor);
		
		jlPitch = new JLabel("Pitch");
		jlPitchAngle = new JLabel("Angle : ");
		jlPitchGyro = new JLabel("Gyro : ");
		jlPitchAcc = new JLabel("Accel : ");
		
		jlRoll = new JLabel("Roll");
		jlRollAngle = new JLabel("Angle : ");
		jlRollGyro = new JLabel("Gyro : ");
		jlRollAcc = new JLabel("Accel : ");
		
		jlGps = new JLabel("GPS");
		jlGpsX = new JLabel("X : ");
		jlGpsY = new JLabel("Y : ");
		jlGpsZ = new JLabel("Z : ");
		
		jlBlankP1 = new JLabel("          ");
		jlBlankP2 = new JLabel("          ");
		jlBlankP3 = new JLabel("          ");
		jlBlankR1 = new JLabel("          ");
		jlBlankR2 = new JLabel("          ");
		jlBlankR3 = new JLabel("          ");
		jlBlankG1 = new JLabel("          ");
		jlBlankG2 = new JLabel("          ");
		jlBlankG3 = new JLabel("          ");
		
		jtfPitchAngle = new JTextField("N/A");
		jtfPitchAngle.setEditable(false);
		jtfRollAngle = new JTextField("N/A");
		jtfRollAngle.setEditable(false);
		jtfPitchGyro = new JTextField("N/A");
		jtfPitchGyro.setEditable(false);
		jtfRollGyro = new JTextField("N/A");
		jtfRollGyro.setEditable(false);
		jtfPitchAcc = new JTextField("N/A");
		jtfPitchAcc.setEditable(false);
		jtfRollAcc = new JTextField("N/A");
		jtfRollAcc.setEditable(false);
		jtfGpsX = new JTextField("N/A");
		jtfGpsX.setEditable(false);
		jtfGpsY = new JTextField("N/A");
		jtfGpsY.setEditable(false);
		jtfGpsZ = new JTextField("N/A");
		jtfGpsZ.setEditable(false);
		
		jlPpm1 = new JLabel("1 : ");
		jlPpm2 = new JLabel("2 : ");
		jlPpm3 = new JLabel("3 : ");
		jlPpm4 = new JLabel("4 : ");
		jlPpm5 = new JLabel("5 : ");
		jlPpm6 = new JLabel("6 : ");
		jlPpm7 = new JLabel("7 : ");
		jlPpm8 = new JLabel("8 : ");
		jlPpm9 = new JLabel("9 : ");
		jtfPpm1 = new JTextField("N/A");
		jtfPpm1.setEditable(false);
		jtfPpm2 = new JTextField("N/A");
		jtfPpm2.setEditable(false);
		jtfPpm3 = new JTextField("N/A");
		jtfPpm3.setEditable(false);
		jtfPpm4 = new JTextField("N/A");
		jtfPpm4.setEditable(false);
		jtfPpm5 = new JTextField("N/A");
		jtfPpm5.setEditable(false);
		jtfPpm6 = new JTextField("N/A");
		jtfPpm6.setEditable(false);
		jtfPpm7 = new JTextField("N/A");
		jtfPpm7.setEditable(false);
		jtfPpm8 = new JTextField("N/A");
		jtfPpm8.setEditable(false);
		jtfPpm9 = new JTextField("N/A");
		jtfPpm9.setEditable(false);
		jlBlankPp1 = new JLabel("          ");
		jlBlankPp2 = new JLabel("          ");
		jlBlankPp3 = new JLabel("          ");
		jlBlankPp4 = new JLabel("          ");
		jlBlankPp5 = new JLabel("          ");
		jlBlankPp6 = new JLabel("          ");
		jlBlankPp7 = new JLabel("          ");
		jlBlankPp8 = new JLabel("          ");
		
		jpPitch = new JPanel();
		jpPitch.setLayout(new GridLayout(1, 10));
		jpRoll = new JPanel();
		jpRoll.setLayout(new GridLayout(1, 10));
		jpGps = new JPanel();
		jpGps.setLayout(new GridLayout(1, 10));
		jpPpm = new JPanel();
		jpPpm.setLayout(new GridLayout(1, 27));
		
		jpPitch.add(jlPitch);
		jpPitch.add(jlBlankP1);
		jpPitch.add(jlPitchAngle);
		jpPitch.add(jtfPitchAngle);
		jpPitch.add(jlBlankP2);
		jpPitch.add(jlPitchGyro);
		jpPitch.add(jtfPitchGyro);
		jpPitch.add(jlBlankP3);
		jpPitch.add(jlPitchAcc);
		jpPitch.add(jtfPitchAcc);
		
		jpRoll.add(jlRoll);
		jpRoll.add(jlBlankR1);
		jpRoll.add(jlRollAngle);
		jpRoll.add(jtfRollAngle);
		jpRoll.add(jlBlankR2);
		jpRoll.add(jlRollGyro);
		jpRoll.add(jtfRollGyro);
		jpRoll.add(jlBlankR3);
		jpRoll.add(jlRollAcc);
		jpRoll.add(jtfRollAcc);
		
		jpGps.add(jlGps);
		jpGps.add(jlBlankG1);
		jpGps.add(jlGpsX);
		jpGps.add(jtfGpsX);
		jpGps.add(jlBlankG2);
		jpGps.add(jlGpsY);
		jpGps.add(jtfGpsY);
		jpGps.add(jlBlankG3);
		jpGps.add(jlGpsZ);
		jpGps.add(jtfGpsZ);
		
		jpSensor.add(jpPitch);
		jpSensor.add(jpRoll);
		jpSensor.add(jpGps);
		
		// PPM
		jpPpm.add(jlPpm1);
		jpPpm.add(jtfPpm1);
		jpPpm.add(jlBlankPp1);
		jpPpm.add(jlPpm2);
		jpPpm.add(jtfPpm2);
		jpPpm.add(jlBlankPp2);
		jpPpm.add(jlPpm3);
		jpPpm.add(jtfPpm3);
		jpPpm.add(jlBlankPp3);
		jpPpm.add(jlPpm4);
		jpPpm.add(jtfPpm4);
		jpPpm.add(jlBlankPp4);
		jpPpm.add(jlPpm5);
		jpPpm.add(jtfPpm5);
		jpPpm.add(jlBlankPp5);
		jpPpm.add(jlPpm6);
		jpPpm.add(jtfPpm6);
		jpPpm.add(jlBlankPp6);
		jpPpm.add(jlPpm7);
		jpPpm.add(jtfPpm7);
		jpPpm.add(jlBlankPp7);
		jpPpm.add(jlPpm8);
		jpPpm.add(jtfPpm8);
		jpPpm.add(jlBlankPp8);
		jpPpm.add(jlPpm9);
		jpPpm.add(jtfPpm9);
		
		tbPpm = new TitledBorder("");
		tbPpm.setTitle(" PPM ");
		tbPpm.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbPpm.setTitleJustification(TitledBorder.CENTER);
		jpPpm.setBorder(tbPpm);
		
		// Attitude
		acAtt = new AttCanvas();
		
		jpAttitude = new JPanel();
		jpAttitude.setLayout(new BorderLayout());
		jpAttitude.add(acAtt, BorderLayout.CENTER);
		
		tbAttitude = new TitledBorder("");
		tbAttitude.setTitle(" Attitude ");
		tbAttitude.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbAttitude.setTitleJustification(TitledBorder.CENTER);
		jpAttitude.setBorder(tbAttitude);
		
		// Graph
		jpGraph = new JPanel();
		tbGraph = new TitledBorder("");
		tbGraph.setTitle(" Graph ");
		tbGraph.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbGraph.setTitleJustification(TitledBorder.CENTER);
		jpGraph.setBorder(tbGraph);
				
		jpGraphPitch = new JPanel();
		jpGraphRoll = new JPanel();
		
		jlGraphPitch = new JLabel("Pitch");
		jlGraphRoll = new JLabel("Roll");
		gcPitch = new GraphCanvas();
		gcRoll = new GraphCanvas();
		
		jpGraphPitch.setLayout(new BorderLayout());
		jpGraphRoll.setLayout(new BorderLayout());
		jpGraphPitch.add(jlGraphPitch, BorderLayout.NORTH);
		jpGraphPitch.add(gcPitch, BorderLayout.CENTER);
		jpGraphRoll.add(jlGraphRoll, BorderLayout.NORTH);
		jpGraphRoll.add(gcRoll, BorderLayout.CENTER);
		
		jpGraph.setLayout(new GridLayout(0, 1));
		jpGraph.add(jpGraphPitch);
		jpGraph.add(jpGraphRoll);
		
		// Log
		jlLogFilename = new JLabel("Filename : ");		
		jtfLogFilename = new JTextField("log.txt");
		jlLogStatus = new JLabel("Log Status : ");
		jtfLogStatus = new JTextField("stopped");
		jtfLogStatus.setEditable(false);
		jlBlankL1 = new JLabel("          ");
		jlBlankL2 = new JLabel("          ");
		jlBlankL3 = new JLabel("          ");
		jlBlankL4 = new JLabel("          ");
		
		jpLog = new JPanel();
		//jpLog.setLayout(new GridLayout(1, 8));
		jpLog.setLayout(new GridLayout(0, 2));
		
		jbStart = new JButton("Start");
		jbStart.addActionListener(this);
		jbStop = new JButton("Stop");
		jbStop.addActionListener(this);
		jbStop.setEnabled(false);
		
		jpLog.add(jlLogFilename);
		jpLog.add(jtfLogFilename);
		jpLog.add(jlBlankL1);
		jpLog.add(jlBlankL2);
		jpLog.add(jbStart);
		jpLog.add(jbStop);
		jpLog.add(jlBlankL3);
		jpLog.add(jlBlankL4);
		jpLog.add(jlLogStatus);
		jpLog.add(jtfLogStatus);
		
		tbLog = new TitledBorder("");
		tbLog.setTitle(" Log ");
		tbLog.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbLog.setTitleJustification(TitledBorder.CENTER);
		jpLog.setBorder(tbLog);

		//	Log Options
		jpLogOption = new JPanel();
		jpLogOption.setLayout(new GridLayout(0, 1));
		cbAngle = new Checkbox("Angle",  false);
		cbGps = new Checkbox("GPS",  false);
		cbTime = new Checkbox("Elapsed Time",  false);
		cbGpadc = new Checkbox("GPADC",  false);
		cbGpppm = new Checkbox("GPPPM",  false);
		cbPitch = new Checkbox("Pitch", false);
		cbRoll = new Checkbox("Roll", false);
		jpLogOption.add(cbAngle);
		jpLogOption.add(cbGps);
		jpLogOption.add(cbTime);
		jpLogOption.add(cbGpadc);
		jpLogOption.add(cbGpppm);
		jpLogOption.add(cbPitch);
		jpLogOption.add(cbRoll);
		
		tbLogOption = new TitledBorder("");
		tbLogOption.setTitle(" Log Options ");
		tbLogOption.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbLogOption.setTitleJustification(TitledBorder.CENTER);
		jpLogOption.setBorder(tbLogOption);

		// Control
		jpControl = new JPanel();
		jpControl.setLayout(new BorderLayout());

		tbControl = new TitledBorder("");
		tbControl.setTitle(" Control ");
		tbControl.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbControl.setTitleJustification(TitledBorder.CENTER);
		jpControl.setBorder(tbControl);
		
		jpControlCenter = new JPanel();
		jpControlCenter.setLayout(new GridLayout(1, 2));
		
		lccLeft = new LControlCanvas();
		rccRight = new RControlCanvas();
		jlControlCh5 = new JLabel("Ch 5 : N/A");
		jpControlCenter.add(lccLeft);
		jpControlCenter.add(rccRight);
		jpControl.add(jpControlCenter, BorderLayout.CENTER);
		jpControl.add(jlControlCh5, BorderLayout.SOUTH);

		// Time
		jpTime = new JPanel();
		jpTime.setLayout(new GridLayout(1,4));
		jtfTimeSec = new JTextField();
		jtfTimeSec.setEditable(false);
		jlTimeSec = new JLabel("sec");
		jtfTimeNSec = new JTextField();
		jtfTimeNSec.setEditable(false);
		jlTimeNSec = new JLabel("nsec");
		jpTime.add(jtfTimeSec);
		jpTime.add(jlTimeSec);
		jpTime.add(jtfTimeNSec);
		jpTime.add(jlTimeNSec);
		
		tbTime = new TitledBorder("");
		tbTime.setTitle(" Elapsed Time ");
		tbTime.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		tbTime.setTitleJustification(TitledBorder.CENTER);
		jpTime.setBorder(tbTime);
		
		// top
		jpTop = new JPanel();
		jpTop.setLayout(new BorderLayout());
		jpTop.add(jpSensor, BorderLayout.CENTER);
		jpTop.add(jpPpm, BorderLayout.SOUTH);
		
		// left
		jpLeft = new JPanel();
		jpLeft.setLayout(new BorderLayout());
		jpLeft.add(jpAttitude, BorderLayout.CENTER);
		jpLeft.add(jpControl, BorderLayout.SOUTH);
		
		// center
		jpCenter = new JPanel();
		jpCenter.setLayout(new BorderLayout());
		jpCenter.add(jpLog, BorderLayout.NORTH);
		jpCenter.add(jpLogOption, BorderLayout.CENTER);
		jpCenter.add(jpTime, BorderLayout.SOUTH);
		
		// put panels into frame
		getContentPane().add(jpTop, BorderLayout.NORTH);
		getContentPane().add(jpGraph, BorderLayout.EAST);
		getContentPane().add(jpLeft, BorderLayout.WEST);
		getContentPane().add(jpCenter, BorderLayout.CENTER);
	}
	
	void init() {
		setSize(900, 600);
		setVisible(true);
	}
	
	void updateAngle(float pitch, float roll) {
		jtfPitchAngle.setText("" + pitch);
		jtfRollAngle.setText("" + roll);
		acAtt.drawLine(pitch, roll);
		gcPitch.updateValue((int)pitch);
		gcRoll.updateValue((int)roll);
		gcPitch.repaint();
		gcRoll.repaint();
	}
	
	void updateGps(float x, float y, float z) {
		jtfGpsX.setText("" + x);
		jtfGpsY.setText("" + y);
		jtfGpsZ.setText("" + z);
	}

	void updateTime(long s, long ns) {
		jtfTimeSec.setText(""+s);
		jtfTimeNSec.setText(""+ns);
	}

	void updateAdc(String gx, String ax, String gy, String ay) {
		jtfPitchGyro.setText(gx);
		jtfPitchAcc.setText(ax);
		jtfRollGyro.setText(gy);
		jtfRollAcc.setText(ay);
	}
	
	void updatePpm(long ch1, long ch2, long ch3, long ch4, long ch5, long ch6, long ch7, long ch8, long ch9) {
		jtfPpm1.setText("" + ch1);
		jtfPpm2.setText("" + ch2);
		jtfPpm3.setText("" + ch3);
		jtfPpm4.setText("" + ch4);
		jtfPpm5.setText("" + ch5);
		jtfPpm6.setText("" + ch6);
		jtfPpm7.setText("" + ch7);
		jtfPpm8.setText("" + ch8);
		jtfPpm9.setText("" + ch9);
		lccLeft.drawPoint(ch4, ch2);
		rccRight.drawPoint(ch1, ch3);
		if(ch5>16000 && ch5<17000) jlControlCh5.setText("Ch 5 : OFF");
		else if(ch5>7000 && ch5<8000) jlControlCh5.setText("Ch 5 : ON");
		else jlControlCh5.setText("Ch 5 : N/A");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbStart) {
			try {
				mon.changeFilename(jtfLogFilename.getText());
			}
			catch(Exception ex) {
				jtfLogStatus.setText("error");
				ex.printStackTrace();
				return;
			}
			mon.changeLogflag(true);
			mon.changeLogOptions(cbAngle.getState(), cbGps.getState(), cbTime.getState(), cbGpadc.getState(), cbGpppm.getState(), cbPitch.getState(), cbRoll.getState());
			jtfLogStatus.setText("started");
			jbStart.setEnabled(false);
			jbStop.setEnabled(true);
		}
		else if(e.getSource()==jbStop) {
			mon.changeLogflag(false);
			jtfLogStatus.setText("stopped");
			jbStart.setEnabled(true);
			jbStop.setEnabled(false);
		}
	}
}

class AttCanvas extends Canvas {
	
	int lx, ly, rx, ry;
	
	AttCanvas() {
		setSize(200, 200);
		lx = 5;
		ly = 100;
		rx = 195;
		ry = 100;
	}
	
	void drawLine(float p, float r) {
		int i = (int)(Math.sqrt(6400 - p*p));
		int x1, y1, x2, y2;
		if(p <= 90.0 && p >= -90.0 && r <=90.0 && r >= -90.0) {
			x1 = -i;
			y1 = (int)p;
			x2 = i;
			y2 = (int)p;
			lx = (int)(x1*Math.cos(Math.PI*(-r)/180)-y1*Math.sin(Math.PI*(-r)/180)) + 90;
			ly = (int)(x1*Math.sin(Math.PI*(-r)/180)+y1*Math.cos(Math.PI*(-r)/180)) + 100;
			rx = (int)(x2*Math.cos(Math.PI*(-r)/180)-y2*Math.sin(Math.PI*(-r)/180)) + 110;
			ry = (int)(x2*Math.sin(Math.PI*(-r)/180)+y2*Math.cos(Math.PI*(-r)/180)) + 100;
		}
		
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawOval(20, 20, 160, 160);
		g.drawLine(lx, ly, rx, ry);
	}
}

class LControlCanvas extends Canvas {
	int x, y;
	
	LControlCanvas() {
		setSize(100, 100);
		x = y = 50;	
	}
	
	void drawPoint(long ch4, long ch2) {
		if(ch4>8500 && ch4<15500) {
			x = 100 - (int)((ch4-8500)/70);
		}
		if(ch2>8300 && ch2<15200) {
			y = (int)((ch2-8300)/67);
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawOval(5, 5, 90, 90);
		g.drawOval(x-2, y-2, 4, 4);
	}
}

class RControlCanvas extends Canvas {
	int x, y;
	
	RControlCanvas() {
		setSize(100, 100);
		x = y = 50;	
	}
	
	void drawPoint(long ch1, long ch3) {
		if(ch1>8500 && ch1<15500) {
			x = (int)((ch1-8500)/70)+3;
		}
		if(ch3>8800 && ch3<17000) {
			y = (int)((ch3-8800)/82)+3;
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawOval(5, 5, 90, 90);
		g.drawOval(x-2, y-2, 4, 4);
	}
}

class GraphCanvas extends Canvas {
	int value[];
	
	GraphCanvas() {
		value = new int[30];
		for(int i=0; i<30; i++) value[i] = 0;
		setSize(300,100);
	}
	
	void updateValue(int v) {
		for(int i=0; i<29; i++) {
			value[i] = value[i+1];
		}
		value[29] = v;
	}
	
	public void paint(Graphics g) {
		g.drawRect(5, 0, 290, 179);
		g.drawLine(5, 89, 295, 89);
		for(int i=1; i<30; i++) {
			if(value[i]>=-90 && value[i]<=90) g.drawLine(5+(i-1)*10, 89 - value[i-1], 5+i*10, 89 - value[i]);
		}
	}
}

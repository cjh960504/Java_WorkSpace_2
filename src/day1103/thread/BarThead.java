package day1103.thread;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class BarThead extends Thread{
	int n;
	int interval;
	JLabel la;
	JProgressBar bar;
	public BarThead(JLabel la, JProgressBar bar, int interval) {
		this.la = la;
		this.bar = bar;
		this.interval = interval;
	}
	@Override
	public void run() {
		while (n<100) {
			n++;
			bar.setValue(n);
			la.setText(n+"%");
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

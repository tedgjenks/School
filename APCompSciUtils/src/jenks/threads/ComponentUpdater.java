package jenks.threads;

import java.awt.Component;

public class ComponentUpdater extends Thread {
	private final Component COMPONENT;
	
	public ComponentUpdater(Component component) {
		COMPONENT = component;
	}
	
	public void run() {
		COMPONENT.repaint();
	}
}

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VoceRobot {
	NetworkTable SmartDashboard;

	public static void main(String[] args) {
		boolean enabled = false;
		boolean quit = false;
		
		voce.SpeechInterface.init("./lib", true, true, "./grammar", "demobot");
		
		//wait for the user to say 'enable' before connecting to robot
		printAndSay("Please say 'enable' to continue connecting to robot");
		while(!enabled) {
			//wait a moment between checks
			try {
				Thread.sleep(50);
			} catch(Exception e) {}
			
			//check for 'enable'
			if(voce.SpeechInterface.getRecognizerQueueSize() > 0) {
				String s = voce.SpeechInterface.popRecognizedString();
				
				if(s.contains("enable")) {
					enabled = true;
				}
			}
		}
		
		//set computer as NetworkTables client
		System.out.println("[NetworkTables] Setting Client mode");
		NetworkTable.setClientMode();
		
		//set IP address to mDNS address
		System.out.println("[NetworkTables] Setting IP address");
		NetworkTable.setIPAddress("roborio-2823-frc.local");
		
		//try to initialize NetworkTable while ignoring any errors
		System.out.println("[NetworkTables] Initializing...");
		NetworkTable.initialize();
		
		//attempt to connect to SmartDashboard NetworkTable
		System.out.println("[NetworkTables] Getting SmartDashboard table...");
		NetworkTable SmartDashboard = NetworkTable.getTable("SmartDashboard");
		
		//print out status of SmartDashboard connection
		System.out.println("[SmartDashboard] Connected: " + SmartDashboard.isConnected());
		
		System.out.println("[NetworkTables] Setup complete (but SmartDashboard may be unavailable)");
		voce.SpeechInterface.synthesize("Setup complete");
		
		while(!quit) {
			//wait a moment between checks
			try {
				Thread.sleep(50);
			} catch(Exception e) {}
			
			//check for new commands
			if(voce.SpeechInterface.getRecognizerQueueSize() > 0) {
				String s = voce.SpeechInterface.popRecognizedString();
				
				SmartDashboard.putString("Command", s);
				SmartDashboard.putBoolean("New Command", true);
				printAndSay(s);
				
				//parse string to find commands
				if(s.contains("disable")) {
					quit = true;
				}
			}
		}
		
		//make absolutely sure the robot was told to stop
		SmartDashboard.putString("Command", "disable");
		SmartDashboard.putBoolean("New Command", true);
		
		//print message and end client program
		System.out.println("[Voce] Stopping all systems. Goodbye.");
		System.exit(0);
	}
	
	//print the message with a [Voce] identifier and speak the message
	public static void printAndSay(String s) {
		System.out.println(s);
		voce.SpeechInterface.synthesize(s);
	}
}

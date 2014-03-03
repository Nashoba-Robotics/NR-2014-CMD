package edu.nr.main.subsystems.RaspberryPi;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;
import java.io.InputStream;
import com.sun.squawk.util.SquawkVector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class RaspberryPi extends Subsystem {
    public void initDefaultCommand() {
        setDefaultCommand(new RaspberryPiIdleCommand());
    }
    
    private RaspberryPi() {
    }
    
    private static RaspberryPi INSTANCE = null;
    private Thread t = null;
    private SocketConnection pieConnection = null;
    private InputStream pieInput = null;
    private boolean connectedToPie = false;
    private SquawkVector pieBytes = new SquawkVector();
    private boolean startedReading = false;
    
    public void listenForPieInput() {
        try {
            while(pieInput.available() > 0) {
                byte input = (byte)pieInput.read();
                
                if(!startedReading)
                    if(input == 10)
                        startedReading = true;
                else {
                    if(input == 0) {
                        startedReading = false;
                        byte[] bytes = new byte[pieBytes.size()];
                        
                        for(int i = 0; i < bytes.length; i++) {
                            bytes[i] = ((Byte)pieBytes.elementAt(i)).byteValue();
                        }
                        
                        String message = new String(bytes);
                        SmartDashboard.putString("Pie Message", message);
                        pieBytes.removeAllElements();
                    }
                    else
                        pieBytes.addElement(Byte.valueOf(input));
                }
            }
        }
        catch (IOException ex) {
            SmartDashboard.putString("Pie Error", ex.toString());
            System.out.println("ERROR TALKING TO PIE");
            connectToPie();
        }
    }
    
    public void connectToPie() {
        if(pieConnection != null) {    
            try {
                pieConnection.close();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        pieConnection = null;
        connectedToPie = false;
        
        if(t != null)
            t.interrupt();
        t = new Thread(new Runnable() {
            public void run() {
                while(connectedToPie == false) {
                    try {
                        if(pieConnection != null)
                            pieConnection.close();
                        
                        pieConnection = (SocketConnection) Connector.open("socket://10.17.68.15:1180");
                        
                        if(pieConnection == null)
                            throw new RuntimeException("ERRROR: Didn't actually connect to pie!!!");
                        
                        pieInput = pieConnection.openInputStream();
                        connectedToPie = true;
                        System.out.println("[ DBG ] CONNECTED TO PIE");
                    }
                    catch(IOException e) {
                        SmartDashboard.putString("Pie Error", e.toString());
                    }
                }
            }
        });
        t.start();
    }
    
    public static RaspberryPi getInstance() {
        if(INSTANCE == null) {
            synchronized(RaspberryPi.class) {
                if(INSTANCE == null) {
                    INSTANCE = new RaspberryPi();
                }
            }
        }
        return INSTANCE;
    }
    
    public boolean isConnectedToPie() {
        return connectedToPie;
    }
    
    public void idle() {
        return;
    }
}
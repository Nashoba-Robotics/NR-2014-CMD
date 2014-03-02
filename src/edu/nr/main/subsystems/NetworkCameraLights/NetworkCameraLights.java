package edu.nr.main.subsystems.NetworkCameraLights;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NetworkCameraLights extends Subsystem implements Printable
{
    private DigitalOutput light1, light2;
    private static NetworkCameraLights INSTANCE = null;
    
    private NetworkCameraLights() {
    }
    
    public void init() {
        light1 = new DigitalOutput(RobotMap.CAMERA_LIGHT_1);
        light2 = new DigitalOutput(RobotMap.CAMERA_LIGHT_2);
    }
    
    public static final NetworkCameraLights getInstance() {
        if(INSTANCE == null) {
            synchronized(NetworkCameraLights.class) {
                if(INSTANCE == null) {
                    INSTANCE = new NetworkCameraLights();
                }
            }
        }
        return INSTANCE;
    }
    
    protected void initDefaultCommand() 
    {
        setDefaultCommand(new NetworkCameraLightsIdleCommand());
    }
    
    public void turnAllOn()
    {
        light1.set(true);
        light2.set(true);
    }
    
    public void turnAllOff()
    {
        light1.set(false);
        light2.set(false);
    }
    
    public void setInner(boolean value)
    {
        light1.set(value);
    }
    
    public void setOuter(boolean value)
    {
        light2.set(value);
    }

    public void sendInfo() 
    {
        SmartDashboard.putData(new NetworkCameraLightsOnCommand());
        SmartDashboard.putData(new NetworkCameraLightsOffCommand());
        SmartDashboard.putData(new NetworkCameraLightsPatternCommand());
    }
    
}

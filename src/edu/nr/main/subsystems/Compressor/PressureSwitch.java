package edu.nr.main.subsystems.Compressor;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

public class PressureSwitch {
    private DigitalInput limit;
    private static PressureSwitch INSTANCE = null;
    private boolean hasInit = false;
    
    private PressureSwitch() {
    }
    
    public void init() {
        if(!hasInit) {
            limit = new DigitalInput(RobotMap.PRESSURE_LIMIT);
        }
        else {
            System.out.println("Limit switch is already instantiated!");
            return;
        }
    }
    
    public static PressureSwitch getInstance() {
        if(INSTANCE == null) {
            synchronized(PressureSwitch.class) {
                if(INSTANCE == null) {
                    INSTANCE = new PressureSwitch();
                }
            }
        }
        return INSTANCE;
    }
    
    public boolean getLimit() {
        return limit.get();
    }
}

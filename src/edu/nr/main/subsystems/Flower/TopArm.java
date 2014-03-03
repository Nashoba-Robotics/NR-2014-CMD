package edu.nr.main.subsystems.Flower;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TopArm extends Subsystem implements Printable {
    private CANJaguar jag;
    private DigitalInput ir;
    private static TopArm INSTANCE = null;
    
    private TopArm() {
    }
    
    public void init() {
        try {
            jag = new CANJaguar(RobotMap.TOP_ARM_JAG);
        } 
        catch (CANTimeoutException ex) {
            System.err.println("Error: couldn't create top arm jag!!");
        }
        ir = new DigitalInput(RobotMap.TOP_ARM_IR_SENSOR);
    }
    
    public static final TopArm getInstance() {
        if(INSTANCE == null) {
            synchronized(TopArm.class) {
                if(INSTANCE == null) {
                    INSTANCE = new TopArm();
                }
            }
        }
        return INSTANCE;
    }
    
    public boolean getIRSensor() {
        return !ir.get();
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new TopArmIdleCommand());
    }

    boolean isRunning = false;
    
    public void run(double speed) {
        isRunning = (Math.abs(speed) > 0);
        try {
            jag.setX(-speed);
        } catch (CANTimeoutException ex) {
            System.out.println("ERROR: Couldn't set top arm jag speed");
        }
    }
    
    public boolean isRunning() {
        return isRunning;
    }

    public void sendInfo() {
        SmartDashboard.putData(this);
        SmartDashboard.putData(new FlowerCloseCommand());
        SmartDashboard.putData(new FlowerBloomCommand());
        SmartDashboard.putData(new TopArmRunCommand());
        SmartDashboard.putData(new TopArmStopCommand());
    }
}

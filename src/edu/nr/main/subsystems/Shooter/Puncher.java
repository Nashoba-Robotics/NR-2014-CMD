package edu.nr.main.subsystems.Shooter;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Puncher extends Subsystem implements Printable {
    private DoubleSolenoid dogGear;
    private static Puncher INSTANCE = null;
    private Puncher() {
    }
    
    public static final Puncher getInstance() {
        if(INSTANCE == null) {
            synchronized(Puncher.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Puncher();
                }
            }
        }
        return INSTANCE;
    }
    
    protected void init() {
        dogGear = new DoubleSolenoid(RobotMap.DOG_GEAR_SOLENOID_DEPLOY, 
                                     RobotMap.DOG_GEAR_SOLENOID_UNDEPLOY);
        dogGear.set(Value.kReverse);
    }
    
    protected void initDefaultCommand() {
        this.setDefaultCommand(new TensionIdleCommand());
    }
    
    public void punch() {
        dogGear.set(Value.kForward);
    }
    
    public void resetDogGear() {
        dogGear.set(Value.kReverse);
    }

    public void sendInfo() {
        SmartDashboard.putData("Puncher", this);
        SmartDashboard.putData("Reset Dog Gear", new ResetDogGearCommand());
        SmartDashboard.putData("Punch Command", new PunchCommand());
    }
}

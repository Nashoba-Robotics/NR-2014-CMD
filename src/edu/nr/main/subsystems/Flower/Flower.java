package edu.nr.main.subsystems.Flower;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Flower extends Subsystem implements Printable {
    private DoubleSolenoid flowerSol;
    private TopArm topArm;
    private static Flower INSTANCE = null;
    
    protected void initDefaultCommand() {
        this.setDefaultCommand(new FlowerIdleCommand());
    }
    
    private Flower() {
    }
    
    public void init() {
        flowerSol = new DoubleSolenoid(RobotMap.FLOWER_SOLENOID_DEPLOY,
                                        RobotMap.FLOWER_SOLENOID_UNDEPLOY);
    }
    
    public static final Flower getInstance() {
        if(INSTANCE == null) {
            synchronized(Flower.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Flower();
                }
            }
        }
        return INSTANCE;
    }
    
    public void close() {
        flowerSol.set(DoubleSolenoid.Value.kForward);
    }
    
    public void bloom() {
        flowerSol.set(DoubleSolenoid.Value.kReverse);
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Flower", this);
        SmartDashboard.putData("Flower Idle Command", new FlowerIdleCommand());
    }
}

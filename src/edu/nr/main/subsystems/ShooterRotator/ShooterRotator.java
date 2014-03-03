package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterRotator extends Subsystem implements Printable {
    private CANJaguar rotationJag;
    private static ShooterRotator INSTANCE = null;
    
    public static final ShooterRotator getInstance() {
        if(INSTANCE == null) {
            synchronized(ShooterRotator.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ShooterRotator();
                }
            }
        }
        return INSTANCE;
    }
    
    public void init() {
        try 
        {
            rotationJag = new CANJaguar(RobotMap.SHOOTER_ROTATION_JAG);
            rotationJag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            rotationJag.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            rotationJag.configPotentiometerTurns(1);
        } 
        catch (CANTimeoutException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    private ShooterRotator() {
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterRotatorIdle());
    }
    
    public double getShooterTiltEncClicks() {
        try {
            return rotationJag.getPosition();
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public boolean isAtDestination(double destination) {
        return getShooterTiltEncClicks() == destination;
    }
    
    public void rotate(double destination) {
        double count = 0;
        double p, i, d = 0;
        try {
            rotationJag.configSoftPositionLimits(destination, 
                                             RobotMap.SHOOTER_ROT_REV_SOFT_LIM);
            if(!isAtDestination(destination)) {
                p = rotationJag.getPosition()/destination;
                i = count * 0.002;
            
                rotationJag.setPID(p, i, d);
                rotationJag.enableControl();
                count++;
            }
            else {
                return;
            }
        }
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stop() {
        try {
            rotationJag.setX(0);
        }
        catch(CANTimeoutException e) {
            System.out.println("Could not stop Jag!");
        }
    }

    public void sendInfo() {
        SmartDashboard.putData("Shooter Rotator", this);
        SmartDashboard.putData("Shooter Rotator Idle", new ShooterRotatorIdle());
        //SmartDashboard.putData("Shooter Rotation (0.4)", new ShooterRotationCommand(0.4,1));
        //SmartDashboard.putData("Shooter Rotation (-0.4)", new ShooterRotationCommand(-0.4,1));
    }
}

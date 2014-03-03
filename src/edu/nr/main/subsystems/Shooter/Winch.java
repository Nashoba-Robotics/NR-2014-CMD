package edu.nr.main.subsystems.Shooter;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Winch extends Subsystem implements Printable {
    private CANJaguar winch;
    private static Winch INSTANCE = null;
    
    private Winch() {
    }
    
    public void init() {
        try {
            winch = new CANJaguar(RobotMap.WINCH_JAG);
            if(RobotMap.USING_LINCODER) {
                winch.configEncoderCodesPerRev(RobotMap.LINCODER_CLICKS);
                winch.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
                winch.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            }
            else {
                winch.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
                winch.setSpeedReference(CANJaguar.SpeedReference.kNone);
                winch.configPotentiometerTurns(RobotMap.POT_TURNS);
            }
            winch.setSafetyEnabled(false);
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Winch getInstance() {
        if(INSTANCE == null) {
            synchronized(Winch.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Winch();
                }
            }
        }
        return INSTANCE;
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new TensionIdleCommand());
    }
    
    public boolean getLimitSwitch() {
        try {
            return winch.getForwardLimitOK();
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void setWinchSpeed(double speed) {
        try {
            winch.setX(speed);
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setWinchLimits(double position) {
        try {
            winch.configSoftPositionLimits(position, 
                                           RobotMap.WINCH_JAG_REV_SOFT_LIM);
            winch.enableControl();
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getWinchVoltage() {
        try {
            return winch.getOutputVoltage();
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public double getLinearEncoderDistance() {
        try {
            return winch.getPosition();
        } 
        catch (CANTimeoutException ex) 
        {
            ex.printStackTrace();
        }
        throw new RuntimeException("Error: Couldn't get winch position");
    }
    
    public void sendInfo() {
        SmartDashboard.putData("Winch", this);
        SmartDashboard.putData("Tension to Distance", 
                                                new TensionToDistanceCommand());
        SmartDashboard.putData("Winch Idle", new TensionIdleCommand());
        SmartDashboard.putData("Tension", new TensionCommand());
    }
}

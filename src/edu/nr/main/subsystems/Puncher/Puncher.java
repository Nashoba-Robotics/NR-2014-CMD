/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.Robot;
import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Puncher extends Subsystem
{
    private static Puncher singleton = null;
    public static Puncher getInstance()
    {
        if(singleton == null)
            singleton = new Puncher();
        
        return singleton;
    }
    
    private CANJaguar winch;
    private DoubleSolenoid dogEar;
    public static final float TENSIONER_REGULAR_SPEED = .9f;
    public static final float TENSIONER_SHOOTING_TENSION = 0.98f;
    
    private Puncher() 
    {
        try 
        {
            winch = new CANJaguar(RobotMap.WINCH_JAG);
            winch.configPotentiometerTurns(1);
            winch.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            winch.setSafetyEnabled(false);
            setWinchLimit(.95f);
        } 
        catch (CANTimeoutException ex) 
        {
            reportCANException(ex);
        }
        dogEar = new DoubleSolenoid(RobotMap.DOG_EAR_SOLENOID_DEPLOY, RobotMap.DOG_EAR_SOLENOID_UNDEPLOY);
        dogEar.set(Value.kReverse);
    }
    
    public void initCAN()
    {
        try
        {
            winch.configPotentiometerTurns(1);
            winch.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            winch.setSafetyEnabled(false);
        }
        catch(CANTimeoutException ex)
        {
            reportCANException(ex);
        }
    }
    
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new TensionIdle());
    }
    
    public boolean getForwardLimitOK()
    {
        try {
            return winch.getForwardLimitOK();
        } catch (CANTimeoutException ex) 
        {
            reportCANException(ex);
        }
        return false;
    }
    
    public void punch() 
    {
        dogEar.set(Value.kForward);
    }
    
    public void resetDogEar()
    {
        dogEar.set(Value.kReverse);
    }
    
    public void setTentionerSpeed(float speed) 
    {
        try {
            winch.setX(speed);
        } catch (CANTimeoutException ex) {
            reportCANException(ex);
        }
    }
    
    public void setWinchLimit(float position)
    {
        try {
            winch.configSoftPositionLimits(position, -2);
        } catch (CANTimeoutException ex) {
            reportCANException(ex);
        }
    }
    
    public double getWinchVoltage()
    {
        try {
            return winch.getOutputVoltage();
        } catch (CANTimeoutException ex) {
            
            reportCANException(ex);
        }
        return -1;
    }
    
    public double getWinchCurrent()
    {
        try {
            return winch.getOutputCurrent();
        } catch (CANTimeoutException ex) {
            
            reportCANException(ex);
        }
        return -1;
    }
    
    public double getLinearEncoderDistance()
    {
        try {
            return winch.getPosition();
        } catch (CANTimeoutException ex) 
        {
            reportCANException(ex);
        }
        return 1;
    }
    
    public void sendInfo() 
    {
        SmartDashboard.putData("Puncher", this);
        SmartDashboard.putData("Reset Dog Gear", new ResetDogEarCommand());
        SmartDashboard.putData("Tension Command (defined Speed)", new TensionCommand());
        SmartDashboard.putData("TensionIdle", new TensionIdle());
        SmartDashboard.putData("Punch Command", new PunchCommand());
        SmartDashboard.putData("Punch Group Command", new PunchGroupCommand());
        SmartDashboard.putData("Tension to Distance", new TensionToDistanceCommand());
    }
    
    private static void reportCANException(Exception ex)
    {
        Robot.reportCANException(ex, "Winch Jag: #" + RobotMap.WINCH_JAG);
    }
}

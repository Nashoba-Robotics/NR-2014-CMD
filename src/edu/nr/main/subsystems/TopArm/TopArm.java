/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.TopArm;

import edu.nr.main.Robot;
import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class TopArm extends Subsystem
{
    private static TopArm singleton = null;
    public static TopArm getInstance()
    {
        if(singleton == null)
            singleton = new TopArm();
        
        return singleton;
    }
            
    private DoubleSolenoid solenoid;
    private CANJaguar jag;
    
    private TopArm()
    {
        solenoid = new DoubleSolenoid(RobotMap.TOP_ARM_SOLENOID_DEPLOY, RobotMap.TOP_ARM_SOLENOID_UNDEPLOY);
        try 
        {
            jag = new CANJaguar(RobotMap.TOP_ARM_JAG);
        } catch (CANTimeoutException ex) 
        {
            reportCANException(ex);
        }
    }
    
    protected void initDefaultCommand()
    {
        setDefaultCommand(new TopArmIdleCommand());
    }
    
    public boolean isDeployed()
    {
        return (solenoid.get() == DoubleSolenoid.Value.kForward);
    }
    
    public void deploy()
    {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void undeploy()
    {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    boolean isRunning = false;
    public void runTopArm(double speed)
    {
        isRunning = (Math.abs(speed) > 0);
        try {
            jag.setX(-speed);
        } catch (CANTimeoutException ex) 
        {
                reportCANException(ex);
        }
    }
    
    public boolean isRunning()
    {
        return isRunning;
    }

    public void sendInfo() 
    {
        SmartDashboard.putData(this);
        SmartDashboard.putData(new TopArmDownCommand());
        SmartDashboard.putData(new TopArmUpCommand());
        SmartDashboard.putData(new TopArmRunCommand());
        SmartDashboard.putData(new TopArmStopCommand());
    }
    
    private void reportCANException(Exception ex)
    {
        Robot.reportCANException(ex, "Top Arm Jag #" + RobotMap.TOP_ARM_JAG);
    }
}

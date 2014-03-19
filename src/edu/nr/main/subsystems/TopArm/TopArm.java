/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.TopArm;

import edu.nr.main.Robot;
import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class TopArm extends Subsystem implements Printable
{
    private DoubleSolenoid solenoid;
    CANJaguar jag;
    //ArmIR ir;
    
    public TopArm()
    {
        solenoid = new DoubleSolenoid(RobotMap.TOP_ARM_SOLENOID_DEPLOY, RobotMap.TOP_ARM_SOLENOID_UNDEPLOY);
        try {
            jag = new CANJaguar(RobotMap.TOP_ARM_JAG);
        } catch (CANTimeoutException ex) {
            System.err.println("Error: couldn't create top arm jag!!");
        }
        //ir = new ArmIR.getInstance();
    }
    
    /*public boolean getIRSensor()
    {
        //Must be inverted because the sensor reports the opposite
        return ir.get();
    }*/
    
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
        } catch (CANTimeoutException ex) {
                Robot.canExceptions++;
//System.out.println("ERROR: Couldn't set top arm jag speed");
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
}

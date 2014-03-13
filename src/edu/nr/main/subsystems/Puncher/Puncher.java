/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Puncher extends Subsystem implements Printable
{

    private CANJaguar winch;
    private DoubleSolenoid dogEar;
    public final float TENSIONER_REGULAR_SPEED = 0.7f;
    
    public Puncher() 
    {
        try 
        {
            winch = new CANJaguar(RobotMap.WINCH_JAG);
            winch.configPotentiometerTurns(1);
            winch.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            winch.setSafetyEnabled(false);
            setWinchLimit(.95f);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        dogEar = new DoubleSolenoid(RobotMap.DOG_EAR_SOLENOID_DEPLOY, RobotMap.DOG_EAR_SOLENOID_UNDEPLOY);
        dogEar.set(Value.kReverse);
    }
    
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new TensionIdle());
    }
    
    public boolean getForwardLimitOK()
    {
        try {
            return winch.getForwardLimitOK();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
    }
    
    public void setWinchLimit(float position)
    {
        try {
            winch.configSoftPositionLimits(position, -2);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public double getWinchVoltage()
    {
        try {
            return winch.getOutputVoltage();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public double getLinearEncoderDistance()
    {
        try {
            return winch.getPosition();
        } catch (CANTimeoutException ex) 
        {
            ex.printStackTrace();
        }
        throw new RuntimeException("Error: Couldn't get winch position");
    }
    
    /*public void setDogEar(Value value)
    {
        dogEar.set(value);
    }*/

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
}

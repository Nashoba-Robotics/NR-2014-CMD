
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.BottomRollers;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class BottomRollers extends Subsystem implements Printable
{
    private RollerPair rollers;
    public BottomRollers()
    {
        CANJaguar roller1 = null;
        try {
            roller1 = new CANJaguar(RobotMap.ROLLER_JAG);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        Victor roller2 = new Victor(RobotMap.ROLLER_VICTOR);
        roller2.setSafetyEnabled(false);
        rollers = new RollerPair(roller1, roller2);
    }
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new StopRollCommand());
    }
    
    public void startRoll()
    {
        rollers.start();
    }
    
    public void stopRoll()
    {
        rollers.stop();
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Bottom Rollers", this);
        //SmartDashboard.putData("First Roller", rollers.getJag());
        //SmartDashboard.putData("Second Roller", rollers.getVictor());
        SmartDashboard.putData("Roll Command", new RollCommand());
        SmartDashboard.putData("Stop Roll", new StopRollCommand());
    }
    
    private class RollerPair
    {
        private CANJaguar jag1;
        private Victor victor;
        public RollerPair(CANJaguar roller1, Victor roller2)
        {
            this.jag1 = roller1;
            this.victor = roller2;
        }
        
        public void start()
        {
            try 
            {
                jag1.setX(0.8);//SmartDashboard.getNumber("Jag Speed"));//speed+0.2);
            } catch (CANTimeoutException ex) 
            {
                System.err.println("Error: Couldn't talk to shooter jag \n" + ex.toString());
            }
            victor.set(-0.37);//SmartDashboard.getNumber("Victor Speed"));//speed);
        }
        
        public void stop()
        {
            try 
            {
                jag1.setX(0);//SmartDashboard.getNumber("Jag Speed"));//speed+0.2);
            } catch (CANTimeoutException ex) 
            {
                System.err.println("Error: Couldn't talk to shooter jag \n" + ex.toString());
            }
            victor.set(0);//SmartDashboard.getNumber("Victor Speed"));//speed);
        }
        
        public CANJaguar getJag()
        {
            return jag1;
        }
        
        public Victor getVictor()
        {
            return victor;
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.BottomRollers;

import edu.nr.main.RobotMap;
import edu.nr.main.commands.InfiniteCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public class BottomRollers extends Subsystem
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
        rollers = new RollerPair(roller1, roller2);
    }
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new RollCommand(0));
    }
    
    public void setRollSpeed(double speed)
    {
        rollers.setSpeed(speed);
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
        
        public void setSpeed(double speed)
        {
            try 
            {
                jag1.setX(speed);
            } catch (CANTimeoutException ex) 
            {
                System.err.println("Error: Couldn't talk to shooter jag \n" + ex.toString());
            }
            victor.set(speed);
        }
    }
}
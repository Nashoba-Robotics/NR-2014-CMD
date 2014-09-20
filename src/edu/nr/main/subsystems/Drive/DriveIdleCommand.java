/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveIdleCommand extends Command
{
    public DriveIdleCommand()
    {
        this.requires(Drive.getInstance());
    }
    
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        Drive.getInstance().drive(0, 0);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }
    
}

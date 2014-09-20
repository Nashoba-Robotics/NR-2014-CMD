/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems;

import edu.nr.main.Robot;
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.Drive.Drive;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.TopArm.TopArm;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Robotics
 */
public class CancelAllCommand extends Command
{
    //By requiring every subsystem, this command boots out any currently running commands for each subsystem.
    //This command also exits immediately, so the default command for each subsystem is started.
    public CancelAllCommand() {
        this.requires(Drive.getInstance());
        this.requires(BottomRollers.getInstance());
        this.requires(TopArm.getInstance());
        this.requires(ShooterRotator.getInstance());
        this.requires(Puncher.getInstance());
    }
     
    protected void initialize() 
    {
    }

    protected void execute() {
    }

    protected boolean isFinished() 
    {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}

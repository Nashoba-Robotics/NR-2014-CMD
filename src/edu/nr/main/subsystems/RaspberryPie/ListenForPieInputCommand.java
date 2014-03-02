/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nr.main.subsystems.RaspberryPie;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 * @author fluffypony
 */
public class ListenForPieInputCommand extends Command {
    
    public ListenForPieInputCommand() {
        super("Listen for Input from the Pie");
        requires(RaspberryPie.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        RaspberryPie.getInstance().listenForPieInput();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
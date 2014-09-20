/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.wpi.first.wpilibj.command.Command;

public class ResetDogEarCommand extends Command
{
    public ResetDogEarCommand()
    {
        super("Reset Dog Ear");
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        Puncher.getInstance().resetDogEar();
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

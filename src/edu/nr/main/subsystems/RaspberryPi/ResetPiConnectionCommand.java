package edu.nr.main.subsystems.RaspberryPi;

import edu.wpi.first.wpilibj.command.Command;

public class ResetPiConnectionCommand extends Command
{
    public ResetPiConnectionCommand() {
        super("Reset Connection w/Raspberry Pie");
        requires(RaspberryPi.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() 
    {
        RaspberryPi.getInstance().connectToPie();
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

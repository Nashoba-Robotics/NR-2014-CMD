package edu.nr.main.subsystems.RaspberryPie;

import edu.wpi.first.wpilibj.command.Command;

public class ResetPieConnectionCommand extends Command
{
    public ResetPieConnectionCommand() {
        super("Reset Connection w/Raspberry Pie");
        requires(RaspberryPie.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() 
    {
        RaspberryPie.getInstance().connectToPie();
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

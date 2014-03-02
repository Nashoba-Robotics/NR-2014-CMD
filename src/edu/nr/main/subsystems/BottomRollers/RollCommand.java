package edu.nr.main.subsystems.BottomRollers;

import edu.wpi.first.wpilibj.command.Command;

public class RollCommand extends Command
{
    public RollCommand() {
        super("Roll Command");
        this.requires(BottomRollers.getInstance());
    }
    
    protected void initialize() {
        
    }

    protected void execute() {
        BottomRollers.getInstance().startRoll();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end(){
    }

    protected void interrupted() {
    }
    
}

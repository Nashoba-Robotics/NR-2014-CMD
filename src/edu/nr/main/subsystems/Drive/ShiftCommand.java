package edu.nr.main.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftCommand extends Command {
    private final Drive drv = Drive.getInstance();
    private final boolean m_direction;
    
    private final boolean FIRST = true;
    private final boolean SECOND = false;
    
    public ShiftCommand(boolean direction) {
        m_direction = direction;
    }
    
    protected void initialize() {
    }

    protected void execute() {
        if(m_direction == FIRST)
            drv.setFirstGear();
        else
            drv.setSecondGear();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
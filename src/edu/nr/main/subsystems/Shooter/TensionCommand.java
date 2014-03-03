package edu.nr.main.subsystems.Shooter;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class TensionCommand extends Command {
   private final Winch winch = Winch.getInstance();
   
   public TensionCommand() {
       super("Tension command");
       requires(winch);
   }
   
   protected void initialize() {
        
   }

    protected void execute() {
        winch.setWinchSpeed(RobotMap.WINCH_REGULAR_SPEED);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        
    }
    
}

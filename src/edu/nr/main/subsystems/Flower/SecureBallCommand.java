package edu.nr.main.subsystems.Flower;

import edu.nr.main.subsystems.BottomRollers.StopRollCommand;
import edu.wpi.first.wpilibj.command.Command;

public class SecureBallCommand extends Command {
    private final TopArm arm = TopArm.getInstance();
    private double init;
    private double current;
    private boolean done = false;
    
    public SecureBallCommand() {
        super("Intake Ball");
    }
    
    protected void initialize() {
        init = System.currentTimeMillis() / 1000;
    }

    protected void execute() {
        new FlowerCloseCommand().start();
        
        if(arm.getIRSensor()) {
            current = System.currentTimeMillis() / 1000;
            
            if(current - init > 0.5) {
                new TopArmStopCommand().start();
                new StopRollCommand().start();
            }
        }
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
    }

    protected void interrupted() {
    }   
}
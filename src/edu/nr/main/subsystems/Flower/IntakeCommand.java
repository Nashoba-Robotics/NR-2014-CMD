package edu.nr.main.subsystems.Flower;

import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.BottomRollers.StopRollCommand;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command
{
    private final BottomRollers rollers = BottomRollers.getInstance();
    private final Flower flower = Flower.getInstance();
    private final TopArm arm = TopArm.getInstance();
    private double init;
    private boolean done = false;
    
    public IntakeCommand() {
        super("Intake Ball");
        requires(rollers);
        requires(flower);
        requires(arm);
    }
    
    protected void initialize() {
        init = System.currentTimeMillis()/1000f;
    }

    protected void execute() {
        new FlowerCloseCommand().start();
        double current = System.currentTimeMillis()/1000f;
        
        if(current - init > 0.5) {
            done = true;
            new StopRollCommand().start();
            new TopArmStopCommand().start();
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
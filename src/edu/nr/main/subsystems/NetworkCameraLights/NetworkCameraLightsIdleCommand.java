package edu.nr.main.subsystems.NetworkCameraLights;

import edu.wpi.first.wpilibj.command.Command;

public class NetworkCameraLightsIdleCommand extends Command {
    public NetworkCameraLightsIdleCommand() {
        super("Network Camera Lights Idle");
        requires(NetworkCameraLights.getInstance());
    }
    
    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
package edu.nr.main.subsystems.NetworkCameraLights;

import edu.wpi.first.wpilibj.command.Command;

public class NetworkCameraLightsOnCommand extends Command {
    private final NetworkCameraLights lights = NetworkCameraLights.getInstance();
    
    public NetworkCameraLightsOnCommand() {
        requires(lights);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        lights.turnAllOn();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

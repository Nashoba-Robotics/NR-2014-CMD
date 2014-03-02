package edu.nr.main.subsystems.NetworkCameraLights;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class NetworkCameraLightsOffCommand extends Command
{
    private final NetworkCameraLights lights = NetworkCameraLights.getInstance();
    public NetworkCameraLightsOffCommand()
    {
        super("Network Camera Lights Off");
        requires(lights);
    }
    protected void initialize() {
    }

    protected void execute()
    {
        lights.turnAllOff();
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

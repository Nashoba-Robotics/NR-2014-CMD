package edu.nr.main.subsystems.NetworkCameraLights;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class NetworkCameraLightsPatternCommand extends CommandGroup {
    
    public NetworkCameraLightsPatternCommand() {
        addParallel(new NetworkCameraLightsOffCommand());
        addSequential(new SetInnerLightCommand(true));
        addSequential(new WaitCommand(.5));
        addParallel(new SetInnerLightCommand(false));
        addSequential(new SetOuterLightCommand(true));
        addSequential(new WaitCommand(0.5));
        addParallel(new NetworkCameraLightsOffCommand());
        addSequential(new WaitCommand(0.5));
        addSequential(new NetworkCameraLightsOnCommand());
    }
}

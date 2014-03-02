package edu.nr.main.subsystems.NetworkCameraLights;

import edu.wpi.first.wpilibj.command.Command;

public class SetInnerLightCommand extends Command {
    private final boolean m_value;
    private final NetworkCameraLights lights = NetworkCameraLights.getInstance();
    
    public SetInnerLightCommand(boolean value) {
        requires(lights);
        m_value = value;
    }
    protected void initialize() {
    }

    protected void execute() {
        lights.setInner(m_value);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

package edu.nr.main.subsystems.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CompressorBase extends Subsystem {
    protected int m_spikePort;
    protected Relay spike = new Relay(m_spikePort);
    protected PressureSwitch limit = null;
    protected void run() {}
    protected void start() {}
    protected void stop() {}
    protected void initDefaultCommand() {}
}

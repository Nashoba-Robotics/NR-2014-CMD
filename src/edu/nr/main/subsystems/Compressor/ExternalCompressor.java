package edu.nr.main.subsystems.Compressor;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ExternalCompressor extends CompressorBase {
    private static ExternalCompressor INSTANCE = null;
    private ExternalCompressor() {
        
    }
    
    public void init(int spikePort) {
        m_spikePort = spikePort;
        spike.setDirection(Relay.Direction.kForward);
        
        limit.init();
    }
    protected void initDefaultCommand() {
        setDefaultCommand(new ExternalCompressorIdle());
    }
    
    protected void run() {
        if(!limit.getLimit()) {
            start();
        }
        else {
            stop();
        }
    }
    
    protected void start() {
        spike.set(Relay.Value.kOn);
    }
    
    protected void stop() {
        spike.set(Relay.Value.kOff);
    }
    
    public void sendInfo() {
        SmartDashboard.putData("External Compressor Start", new ExternalCompressorStart());
        SmartDashboard.putData("External Compressor Stop", new ExternalCompressorStop());
        SmartDashboard.putData("External Compressor", this);
    }
    
    public static ExternalCompressor getInstance() {
        if(INSTANCE == null) {
            synchronized(ExternalCompressor.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ExternalCompressor();
                }
            }
        }
        return INSTANCE;
    }
}

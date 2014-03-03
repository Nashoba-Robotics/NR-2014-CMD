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
        setDefaultCommand(new ExternalCompressorRun());
    }
    
    public void run() {
       if(SmartDashboard.getBoolean("Auto Compressor")) {
            if(!limit.getLimit()) {
             start();
            }
            else {
                stop();
            }
       }
    }
    
    public void start() {
        spike.set(Relay.Value.kOn);
    }
    
    public void stop() {
        spike.set(Relay.Value.kOff);
    }
    
    public boolean getLimit() {
        return limit.getLimit();
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

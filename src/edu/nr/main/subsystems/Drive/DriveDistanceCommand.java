package edu.nr.main.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistanceCommand extends Command {
    private final Drive drv = Drive.getInstance();
    private int count;
    private boolean doneDriving;
    private float m_distance, m_speed;
    private double initialGyroAngle;
    private double lastEncoderDistance = 0;
    private double x=0, y=0;
    
    private DriveDistanceCommand() {
    }
    
    public DriveDistanceCommand(float distance, float speed) {
        super("DriveDistanceCommand");
        m_distance = (float) SmartDashboard.getNumber("Drive Distance");
        m_speed = speed;
        requires(drv);
    }
    
    protected void initialize() {
        doneDriving = false;
        count = 0;
        drv.resetEncs();
        drv.resetGyro();
        drv.setFirstGear();
        initialGyroAngle = drv.getGyroAngle();
    }

    protected void execute() {
        double angle = drv.getGyroAngle();
        SmartDashboard.putNumber("Gyro", angle);
        double turnAngle = 0;
        if(Math.abs(angle) > 0.3)
        {
            if(angle < 0)
                turnAngle = Math.min(0.5, -angle*0.05);
            else
                turnAngle = Math.min(-0.5, -angle*0.05);
        }
        
        double ave = drv.getAverageEncoderDistance();
        SmartDashboard.putNumber("Encoder Ave", ave);
        /*Position calculations
        double delta_x_r = (ave-lastEncoderDistance);
        double deltax = delta_x_r * Math.cos(-angle);
        double deltay = delta_x_r * Math.sin(-angle);
        x += deltax;
        y += deltay;*/
        
        count++;
        double err = m_distance - ave;

        double proportionalStopDistance = m_distance / 4 * 3;
        double proportionalSpeed = ((1/(proportionalStopDistance)) * err) * m_speed;
        double integralSpeed = count * m_speed/Math.abs(m_speed) * 0.002;
        double newSpeed = Math.min(m_speed, proportionalSpeed + integralSpeed);
        SmartDashboard.putNumber("TurnAngle", turnAngle);
        SmartDashboard.putNumber("I Value", integralSpeed);
        SmartDashboard.putNumber("New Speed", newSpeed);
        drv.drive(-newSpeed, turnAngle);

        
        /*SmartDashboard.putNumber("Encoder 1", val1);
        SmartDashboard.putNumber("Encoder 2", val2);
        SmartDashboard.putNumber("Gyro", angle);
        SmartDashboard.putNumber("turn velocity", turnAngle);
        SmartDashboard.putNumber("Driving Speed", newSpeed);
        SmartDashboard.putNumber("Val1", e1.getRate());
        SmartDashboard.putNumber("Val2", e2.getRate());
        SmartDashboard.putNumber("Integral Speed", integralSpeed);
        SmartDashboard.putNumber("Count", count);*/
        
        lastEncoderDistance = ave;
    }

    protected boolean isFinished() {
        return drv.getAverageEncoderDistance() > m_distance;
    }

    protected void end() {
        drv.drive(0, 0);
    }

    protected void interrupted() {
        drv.drive(0, 0);
    }
}
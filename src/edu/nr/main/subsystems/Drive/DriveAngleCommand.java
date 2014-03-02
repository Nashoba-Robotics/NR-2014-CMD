package edu.nr.main.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAngleCommand extends Command {
    private final double m_speed;
    private double m_angle;
    private int count=0;
    private double initialGyroAngle;
    private double error;
    private boolean wasLastNegative =false;
    private final Drive drv = Drive.getInstance();
    
    private DriveAngleCommand() {
        m_angle=-1; 
        m_speed=-1;
    }
    
    public DriveAngleCommand(double angle, double speed) {
        m_speed = speed * (angle/Math.abs(angle));
        requires(drv);
    }
    
    protected void initialize() {
        m_angle = SmartDashboard.getNumber("Angle");
        count = 0;
        drv.resetEncs();
        drv.resetGyro();
    }

    protected void execute() {
        double currentAngle = drv.getGyroAngle();
        error = m_angle - currentAngle;
        SmartDashboard.putNumber("Error", error);
        double stopAngle = 50;
        double proportionalSpeed = Math.min(m_speed, (1/stopAngle)*error * m_speed);
        
        double integralSpeed =  count * error/Math.abs(error) * 0.005;
        double commandingSpeed = proportionalSpeed + integralSpeed;
        drv.drive(0, -commandingSpeed);
        
        SmartDashboard.putNumber("Proportional", proportionalSpeed);
        SmartDashboard.putNumber("Integral", integralSpeed);
        SmartDashboard.putNumber("Count", count);
        SmartDashboard.putNumber("Gyro", drv.getGyroAngle());
        SmartDashboard.putNumber("Error", error);
        /*SmartDashboard.putNumber("Commanding angle", commandingSpeed);
        SmartDashboard.putNumber("Gyro", currentAngle);
        SmartDashboard.putNumber("Final Speed", finalSpeed);
        SmartDashboard.putNumber("Integral Speed", integralSpeed);
        SmartDashboard.putNumber("Gyro Rate", gyro.getRate());*/
        
        if(error < stopAngle)
            count++;
        
        //If we overshot, reset the integral value because we will just shoot back and forth
        //faster and faster otherwise
        if(wasLastNegative != (error < 0))
            count = 0;
        wasLastNegative = (error < 0);
    }

    protected boolean isFinished() {
        double epsilon = 2;
        return ((Math.abs(error) < epsilon) && (Math.abs(drv.getGyroRate()) < 10));
    }

    protected void end() {
    }

    protected void interrupted(){
    }
}

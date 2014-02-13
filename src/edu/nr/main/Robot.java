/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.nr.main;


import edu.nr.main.oi.OI;
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.Compressor.Compressor;
import edu.nr.main.subsystems.Compressor.CompressorStart;
import edu.nr.main.subsystems.Compressor.CompressorStop;
import edu.nr.main.subsystems.Drive.Drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.nr.main.subsystems.Flower.Flower;
import edu.nr.main.subsystems.Puncher.PunchCommand;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public static Drive drive = null;
    public static BottomRollers rollers = null;
    public static Flower flower = null;
    public static Puncher puncher = null;
    public static ShooterRotator shooterRotator = null;
    public static Compressor compressor;
    
    public void robotInit() 
    {
        if(drive == null)
            drive = new Drive();
        if(compressor == null)
            compressor = new Compressor();
        if(rollers == null)
            rollers = new BottomRollers();
        if(flower == null)
            flower = new Flower();
        if(puncher == null)
            puncher = new Puncher();
        if(shooterRotator == null)
            shooterRotator = new ShooterRotator();
        SmartDashboard.putNumber("Tensioner Speed", 0);
    }

    public void autonomousInit() 
    {
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
        /*SmartDashboard.putData("DriveDistanceCommand", new DriveDistanceCommand(10, 0.8f));
        SmartDashboard.putData("DriveAngleCommand", new DriveAngleCommand(90, 0.6));*/
        SmartDashboard.putData(new CompressorStart());
        SmartDashboard.putData(new CompressorStop());
        SmartDashboard.putData(new PunchCommand());
        SmartDashboard.putData("Drive", drive);
        OI.init();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

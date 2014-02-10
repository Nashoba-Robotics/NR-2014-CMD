/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.nr.main;


import edu.nr.main.commands.OneRunCommand;
import edu.nr.main.oi.OI;
import edu.nr.main.subsystems.Drive.DriveDistanceCommand;
import edu.nr.main.subsystems.Drive.Drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.nr.main.subsystems.Drive.DriveAngleCommand;
import edu.nr.main.subsystems.Pneumatics.Pneumatics;
import edu.nr.main.subsystems.SolenoidSys;

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
    public static SolenoidSys solenoidSys = null;
    public static Pneumatics pneumatics = null;
    public void robotInit() 
    {
        SmartDashboard.putNumber("Angle", 90);
        if(drive == null)
            drive = new Drive();
        if(solenoidSys == null)
            solenoidSys = new SolenoidSys();
        if(pneumatics == null)
            pneumatics = new Pneumatics();
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
        SmartDashboard.putData(new OneRunCommand(pneumatics.startCompressor, pneumatics));
        SmartDashboard.putData(new OneRunCommand(pneumatics.stopCompressor, pneumatics));
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

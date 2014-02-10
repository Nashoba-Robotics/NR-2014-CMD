/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.commands;

/**
 *
 * @author colin
 */
public abstract class NamedRunnable implements Runnable
{
    public String name;
    public NamedRunnable(String name)
    {
        this.name = name;
    }
}

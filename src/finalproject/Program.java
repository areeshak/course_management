/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Aresha Kashif
 */
public class Program 
{
    String programName;
    AdminLinkedList courseList = new AdminLinkedList();                                                           //Linked list of course type

    Program(String programName)
    {
       this.programName = programName;
    }
}

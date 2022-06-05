/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;


/**
 *
 * @author Aresha Kashif
 */
public class Admin
{
    Program[] programs;
    static int programCounter = 0;
    String line;

    Admin(int size)
    {
        programs = new Program[size];
        readFile();
    }
    
    private void readFile()
    {
        String line, programName = "", coursesName = "";
        int index;
        try
        {
           FileReader data = new FileReader("ProgramsDetails.txt");
           BufferedReader FileInput = new BufferedReader(data);
           while((line = FileInput.readLine()) != null)
           {
               index = line.indexOf(":");
               if(index != -1)
               {
                   programName = line.substring(0,index);                       
                   coursesName = line.substring(index+2);
               }
               addProgram(programName);         //add programs name to array of rograms
               if(!coursesName.equals(" "))                                      
               {
                   String[] courseList = coursesName.split(",");
                   for(int i =0; i < courseList.length; i++)
                   {
                       addCourse(programName,courseList[i],0,false);                  //add course name in courses linked list
                   }
               }
           }
           FileInput.close();
           data.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    private void updateFile()
    {
        try
        {
            FileOutputStream clearFile = new FileOutputStream("ProgramsDetails.txt");         //clear all data in text file
            clearFile.close();
            FileWriter updateWriter = new FileWriter("ProgramsDetails.txt");
            for(int i = 0; i < programCounter; i++)
            {
                if(programs[i] != null && programs[i].programName != null)
                {
                    updateWriter.write(programs[i].programName + ": ");
                    if(programs[i].courseList != null)
                    {
                        updateWriter.write(programs[i].courseList.toString() + "\n");
                    }
                    else
                    {
                        updateWriter.write("\n");
                    }
                }
            }
            updateWriter.close();
        }
        catch(Exception e)
        {
           System.out.println(e); 
        }
    }

    public boolean addProgram(String programName)                             
    {
        int i;
        int check = findProgram(programName);                                   //check if program already present
        if(check == -1)
        {
            for(i = 0; i < programs.length; i++)                                //find free space in array
            {
                if(programs[i] == null)
                {
                  break;
                }
            }
            programCounter++;
            programs[i] = new Program(programName);                             //insert program name in array of programs
            return true;
        }
        else
        {
            System.out.println("Program already present");
            return false;
        }
    }

    private int findProgram(String programName)
    {
        for(int i = 0; i < programCounter; i++)
        {
            if(programs[i].programName != null && programs[i].programName.equals(programName))
            {
                return i;
            }
        }
        return -1;
    }

    public void addCourse(String programName, String courseName, int studentsCapacity, boolean status)
    {
        int i = findProgram(programName);
        if(i == -1)
        {
            addProgram(programName);                                            //Adding program in program array when not already present
            i = findProgram(programName);
        }
        programs[i].courseList.insert(courseName, studentsCapacity);            //insert course in linked list
        if(status == true)
        {
            updateFile();                                                       //if admin insert new course add in text file
        }
    }

    public boolean deleteProgram(String programName)
    {
        int i = findProgram(programName);
        if(i != -1)                                                             //removes program name from the list and unlink connection with linked list
        {
            programs[i].programName = null;
            programs[i].courseList.delete(null);                                                                                           //update text file
            updateFile();
            return true;
        }
        else
        {
            System.out.println("Program not found");
            return false;
        }
    }

    public boolean deleteCourse(String programName, String courseName)
    {
        int i = findProgram(programName);
        if(i != -1)
        {
            programs[i].courseList.delete(courseName);                          //deletes course name from linked list
            updateFile();
            return true;
        }
        else
        {
            System.out.println("Program not found");
            return false;
        }
    }

    public String findCourseList(String programName)
    {
        int i = findProgram(programName);
        if(i != -1)
        {
            String str = programs[i].courseList.find();                         //finds all courses under the program name
            return str;
        }
        return null;
    }
    
    public String programList()
    {
        String str = "";
        for(int i = 0; i < programCounter; i++)
        {
            if(programs[i].programName != null)
            {
                str =str + programs[i].programName + "\n";
            }
        }
        return str;
    }

    @Override
    public String toString() {
        String programsList = "";
        for(int i = 0 ; i < programCounter; i++)
        {
            if(programs[i] != null && programs[i].programName != null)
            {
                programsList = programsList + "Program: "  + i + " "+ programs[i].programName + "\n";
                if(programs[i].courseList != null)
                {
                    programsList = programsList + programs[i].courseList.toString() + "\n";
                }
            }
        }
        return programsList;
    }
}
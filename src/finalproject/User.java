/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import static finalproject.Admin.programCounter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Aresha Kashif
 */
public class User
{
    UserLinkedList[] userList;
    int s;

    User(int size)
    {
        s = size + (size/3);
        userList = new UserLinkedList[s];
        readFile();
    }
    
    private void readFile()
    {
        String line, userName = "", userPassword = "",coursesName = "";
        int indexMiddle,indexEnd;
        try
        {
           FileReader data = new FileReader("UserDetails.txt");
           BufferedReader FileInput = new BufferedReader(data);
           while((line = FileInput.readLine()) != null)
           {
               indexMiddle = line.indexOf(",");
               indexEnd = line.indexOf(":");
               if(indexMiddle != -1 && indexEnd != -1)
               {
                   userName = line.substring(0,indexMiddle);                       
                   userPassword = line.substring(indexMiddle+1,indexEnd);
                   coursesName = line.substring(indexEnd+1);
               }
               insertUser(userName,userPassword);                        //add user name to hashing table
               if(!coursesName.trim().equals(""))                                      
               {
                   String[] courseList = coursesName.split(",");
                   for(int i =0; i < courseList.length; i++)
                   {
                       indexMiddle = courseList[i].indexOf("-");    
                       enrollCourse(userName,courseList[i].substring(0,indexMiddle),courseList[i].substring(indexMiddle+1),false);
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
            FileOutputStream clearFile = new FileOutputStream("UserDetails.txt");         //clear all data in text file
            clearFile.close();
            FileWriter updateWriter = new FileWriter("UserDetails.txt");
            updateWriter.write(toString());                                                   //add all user data in text file
            updateWriter.close();
        }
        catch(Exception e)
        {
           System.out.println(e); 
        }
    }

    private int keyToInt(String name)
    {
        int sum = 0;
        for(int i = 0; i < name.length(); i++)
        {
            sum = sum + name.charAt(i);
        }
        return sum;                                                                                                     //returns sum of characters of the user name
    }

    private int Hash(String name)
    {
        int sum = keyToInt(name);
        return sum % userList.length;                                                                                   //return the sum mod alenghth of user list array
    }

    public void insertUser(String name, String password)
    {
        int h = Hash(name);                                                                                             //finds position in array
        if(userList[h] == null)
        {
            userList[h] = new UserLinkedList();                                                                         //creates a linked list at the array position if not made before
        }
        userList[h].insert(name, password);
    }

    public void removeUser(String name)
    {
        int h = Hash(name);
        if(userList[h] != null)
        {
            boolean check = userList[h].delete(name);
            if(check == true)
            {
                updateFile();                                                   //remove user from data system
            }
        }
        else
        {
            System.out.println("User not found");
        }
    }

    public int find(String userName)
    {
        int h = Hash(userName);                                                  //find position of user name in hash table
        if(userList[h] != null)
        {
           return h;
        }
        return -1;
    }

    public void enrollCourse(String userName, String programName, String courseName, boolean check)
    {
        int h = find(userName);
        if(h != -1)
        {
           userList[h].addCourse(programName,userName,courseName);     //if user name found, course added in user linked list class
           if(check == true)
            {
                updateFile();
            }
        }
        else
        {
            System.out.println("User not found");                                        //User not found
        }
       
    }

    public boolean unEnrollCourse(String userName, String programName, String courseName)
    {
        boolean check = false;
        int h = find(userName);
        if(h != -1)
        {
            check = userList[h].deleteCourse(userName,courseName);     //if user name found, course removed in user linked list class
            if(check == true)
            {
                updateFile();                                                   //update data in system if deletion successfull
            }
        }
        else
        {
            System.out.println("User not found");                                                                       //User not found
        }
        return check;
    }

    public String enrolledCourseList(String userName)
    {
        int h = find(userName);
        if(h != -1)
        {
            return userList[h].findCourseList(userName);    //if user name found, find courses enrolled by user
        }
        else
        {
            System.out.println("User not found");           //User not found
        }
        return null;
    }

    @Override
    public String toString()
    {
        String str = "";
        for(int i = 0; i < userList.length; i++)
        {
            if(userList[i] != null)
            {
                str = str + userList[i].toString();
            }
        }
        return str;
    }
}
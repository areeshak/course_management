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

class UserCourseNode
{
    String programName, courseName;
    UserCourseNode next;

    UserCourseNode(String p,String c)
    {
        programName = p;
        courseName = c;
    }
}

public class UserCourseLinkedList 
{
UserCourseNode head;

    public UserCourseNode insert(String p, String c, UserCourseNode head )                                                                             //insert program name in user inked list
    {
        UserCourseNode n = new UserCourseNode(p,c);
        this.head = head;
        if(head == null)                                                 //if list is empty
        {
            head = n;
            return n;
        }
        else
        {
            UserCourseNode temp = head;
            UserCourseNode prev = head;
            while (temp.next != null)
            {
                prev = temp;
                temp = temp.next;
            }
            temp.next = n;                                                                                              //insert at end
        }
        return null;
    }

    public UserCourseNode delete(String d, UserCourseNode head)
    {
        UserCourseNode temp = head;
        UserCourseNode temp_previous = null;
        while(temp.next != null && !temp.courseName.equals(d))
        {
            temp_previous = temp;
            temp = temp.next;
        }
        
        if(temp.courseName.equals(d))
        {
            if(temp == head)                                                            //remove head node
            {
                head = temp.next;
            }
            else
            {
                temp_previous.next = temp.next;
            }
            return head;
        }
        else
        {
            System.out.println("Data not found");
            return null;
        }
        
    }

    public String display(UserCourseNode head)
    {
        String str = "";
        UserCourseNode temp = head;
        while (temp != null)
        {
            str = str + temp.programName + "-" + temp.courseName + ",";
            temp = temp.next;
        }
        return str;
    }    
}

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

class UserNode
{
    String userName;
    String password;
    UserNode next;
    UserCourseNode reference;

    UserNode(String x, String p)
    {
        userName = x;
        password = p;
    }
}

public class UserLinkedList
{
    UserCourseLinkedList userCourseList;
    UserNode head;
    UserNode tail;

    public void insert(String d, String p )                 //insert user name and password in linked list
    {
        this.head = head;
        UserNode n = new UserNode(d,p);
        if(head == null && tail == null)
        {
            head = n;
        }
        else if(tail.next == null)
        {
            tail.next = n;                          //insertion at end
        }
        tail = n;
    }

    public void addCourse(String programName,String userName, String courseName)                                                           //Add course to user's linked list
    {
        UserNode temp = find(userName);
        UserCourseNode temp_c;
        userCourseList = new UserCourseLinkedList();                            //new course added in course linked list of user's
        temp_c = userCourseList.insert(programName,courseName, temp.reference);
        if(temp_c != null)
        {
            temp.reference = temp_c;                                            //add users course linked list reference in users name linked list
        }
    }

    public boolean deleteCourse(String userName, String courseName)
    {
        UserNode temp = find(userName);
        UserCourseNode temp_c = userCourseList.delete(courseName, temp.reference);
        if(temp_c != temp.reference)                                            //update temp refrence if head changed 
        {
            temp.reference = temp_c;                                            //add users course linked list reference in users name linked list
        }
         return true;
    }

    public String findCourseList(String userName)
    {
        UserNode temp = find(userName);
        try{
             return userCourseList.display(temp.reference);
        }
        catch(Exception e)
        {
            return null;                
        }
    }

    public UserNode find(String userName)                                                                               //find reference of user name in users linked list
    {
        UserNode temp = head;
        while (temp.next != null && temp.userName != userName)                                                                   //temp != null
        {
            temp = temp.next;
        }
        return temp;
    }

    public boolean delete(String d)
    {
        
            UserNode temp = head;
            UserNode temp_previous = null;
            while(temp.next != null && !temp.userName.equals(d))
            {
                temp_previous = temp;
                temp = temp.next;
            }
            if(temp.userName.equals(d))
            {
                if(temp == head)                                //remove head node
                {
                    head = temp.next;
                }
                else
                {
                    temp_previous.next = temp.next;
                }
                temp.reference = null;
                return true;
            }
            else
            {
                System.out.println("User not found");
                return false;
            }
        

    }

    @Override
    public String toString()
    {
        String str = "";
        UserNode temp = head;
        while (temp != null)
        {
            if(temp.reference != null)
            {
                str = str + temp.userName + "," + temp.password + ":" + userCourseList.display(temp.reference) + "\n";
            }
            else
            {
                str = str + temp.userName + "," + temp.password + ":" + "\n";
            }
            temp = temp.next;
        }
        return str;
    }
}
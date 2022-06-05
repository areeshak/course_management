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

class Node
{
    String courseName;
    int studentsCapacity;
    Node next;

    Node(String x, int sc)
    {
        courseName = x;
        studentsCapacity = sc;
    }
}

public class AdminLinkedList
{
    Node head;
    Node tail;

    public void insert(String d, int sc)                                             //insert course if called by admin class
    {
        this.head = head;
        Node n = new Node(d,sc);
        if(head == null)
        {
            head = n;
        }
        else if(tail.next == null)
        {
            tail.next = n;                                      //insertion at end
        }
        tail = n;
    }

    public String find()
    {
        String str = "";
        Node temp = head;
        while (temp != null)
        {
            str = str + temp.courseName + "  ";
            temp = temp.next;
        }
        return str;
    }

    public void delete(String d)
    {
        if(d == null)
        {
            head = null;
        }
        else
        {
            Node temp = head;
            Node temp_previous = null;
            while(temp.next != null && !temp.courseName.equals(d))
            {               
                temp_previous = temp;
                temp = temp.next;
            }
            if(temp.courseName.equals(d))
            {
                if(temp == head)                                                 //remove head node
                {
                    head = temp.next;
                }
                else
                {
                    temp_previous.next = temp.next;
                }
            }
            else
            {
                System.out.println("Data not found");
            }
        }
    }

    @Override
    public String toString()
    {
        String str = "";
        Node temp = head;
        while (temp != null)
        {
            str = str + temp.courseName + ",";
            temp = temp.next;
        }
        return str;
    }
}
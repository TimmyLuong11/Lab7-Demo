/*
 * GuiTestUtils
 */

//package gov.fnal.ichiro.test;

import java.awt.*;
import javax.swing.*;

/**
 * Source:
 * http://www.javaworld.com/article/2073056/swing-gui-programming/automate-gui-tests-for-swing-applications.html
 * 
 * @author Unknown
 * @version 2073056
 *
 */
public class GuiTestUtils {

    /** Running counter for the number of children */
    static int counter;

    /** Get the child component with the specified name and parent
     * 
     * @param parent the component which the component of the given name resides under
     * @param name the name of the component of interest
     * @return the desired component, of the given name, under the provided parent
     * */
    public static Component getChildNamed(Component parent, String name) 
    {
        // Debug line
        //System.out.println("Class: " + parent.getClass() +
        //" Name: " + parent.getName());

        if (name.equals(parent.getName())) { 
            return parent;
        }

        if (parent instanceof Container) 
        {
            Component[] children = (parent instanceof JMenu) ?
                            ((JMenu)parent).getMenuComponents() :
                                ((Container)parent).getComponents();

            for (int i = 0; i < children.length; ++i) 
            {
                Component child = getChildNamed(children[i], name);
                if (child != null) { return child; }
            }
        }

        return null;
    }

    /** Get the child component at the specified index in the parent's
     * child list
     * 
     * @param parent the component which the component of the given name resides under
     * @param klass the class of the component of interest
     * @param index the location in the parent's child list the component resides
     * @return the desired component, of the given index, under the provided parent
     * */
    public static Component getChildIndexed(Component parent, String klass, int index) 
    {
        counter = 0;

        // Step in only owned windows and ignore its components in JFrame
        if (parent instanceof Window) 
        {
            Component[] children = ((Window)parent).getOwnedWindows();

            for (int i = 0; i < children.length; ++i) 
            {
                // take only active windows
                if (children[i] instanceof Window && !((Window)children[i]).isActive()) 
                { 
                    continue;
                }

                Component child = getChildIndexedInternal(children[i], klass, index);
                if (child != null) 
                { 
                    return child;
                }
            }
        }

        return null;
    }

    /** Get the child component at the specified index in the parent's
     * child list
     * 
     * @param parent the component which the component of the given name resides under
     * @param klass the class of the component of interest
     * @param index the location in the parent's child list the component resides
     * @return the desired component, of the given index, under the provided parent
     * */
    private static Component getChildIndexedInternal(Component parent, String klass, int index)
    {
        // Debug line
        //System.out.println("Class: " + parent.getClass() +
        //" Name: " + parent.getName());

        if (parent.getClass().toString().endsWith(klass)) 
        {
            if (counter == index) 
            { 
                return parent;
            }
            ++counter;
        }

        if (parent instanceof Container) 
        {
            Component[] children = (parent instanceof JMenu) ?
                            ((JMenu)parent).getMenuComponents() : ((Container)parent).getComponents();

            for (int i = 0; i < children.length; ++i) 
            {
                Component child = getChildIndexedInternal(children[i], klass, index);
                if (child != null) 
                { 
                    return child;
                }
            }
        }

        return null;
    }
}
// vim: set ai sw=4 ts=4:


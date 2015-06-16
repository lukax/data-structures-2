/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanager;

import java.util.ArrayList;

/**
 *
 * @author Thiago
 */
public class Entity {
    
    public static boolean FREE = true;
    public static boolean OCCUPIED = false;
    
    private ArrayList<Attribute> attributes;
    private int next;
    private boolean flag;
    private int recordSize;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanager;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Thiago
 */
public class HashStorage {
    
    private final int recordSize;
    private final int next;


    public HashStorage(int next) {
        this.next = next;
        this.recordSize = 4;
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    public int getNext() {
        return this.next;
    }

    public void write(RandomAccessFile out) throws IOException {
        out.writeInt(this.next);
    }

    public static HashStorage read(RandomAccessFile in) throws IOException {
        return new HashStorage(in.readInt());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;        
        hash = 71 * hash + this.next;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final HashStorage other = (HashStorage) obj;
        
        if (this.next != other.next) {
            return false;
        }
        
        return true;
    }
}

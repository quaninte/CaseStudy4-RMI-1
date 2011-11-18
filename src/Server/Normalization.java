/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author quanmt
 */
public interface Normalization extends Remote {
    
    public String normalize(String text) throws RemoteException;
    
}

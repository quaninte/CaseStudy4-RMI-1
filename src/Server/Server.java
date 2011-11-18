/*
 * windows: start rmiregistry
 * unix: rmiregistry &
 */
package Server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.regex.*;

/**
 *
 * @author quanmt
 */
public class Server extends UnicastRemoteObject implements Normalization {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public String normalize(String text) throws RemoteException {
        
        String patternStr = "[ ]{2,}";
        String replacementStr = " ";
        
        // Compile regular expression
        Pattern pattern = Pattern.compile(patternStr);
        
        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll(replacementStr);
        
        // split to many lines
        String[] lines = text.split("\n");
        
        String result = "";
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            result += line.substring(0, 1).toUpperCase() + line.substring(1) + "\n";
        }
        
        return result;
    }
    
    public static void main(String args[]) throws Exception {
	
        Server server = new Server();

        LocateRegistry.createRegistry(12345);
        Naming.rebind("rmi://localhost:12345/normalize", server);
        
        System.out.print("Server is running ...");
        
    }
    
}

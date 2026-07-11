import java.rmi.Remote;
import java.rmi.RemoteException;

interface RemoteMethods extends Remote {
    String register(String name, String faculty, int age, int rollNo) throws RemoteException;
    String update(int rollNo, String name, String faculty, int age) throws RemoteException;
    String list() throws RemoteException;
    String search(int rollNo) throws RemoteException;
    String delete(int rollNo) throws RemoteException;
}
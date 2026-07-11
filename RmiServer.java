import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) throws Exception {
        PSRemoteObject obj = new PSRemoteObject();
        Registry rg = LocateRegistry.createRegistry(1008);
        rg.rebind("StudentService", obj);
        System.out.println("Server Started");
    }
}
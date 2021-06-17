
import java.rmi.*;


public interface InterfaceSeguro extends Remote{
    public String getSeguro() throws RemoteException;
}

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import model.Seguro;
public class Servidor extends UnicastRemoteObject implements InterfaceSeguro{

    public Servidor() throws RemoteException {
        super();
    }           
        
    public static void main(String[] args) throws RemoteException {
           Registry reg = LocateRegistry.createRegistry(9999);
           reg.rebind("hi_server", new Servidor());
           System.out.println("Servidor RMI iniciado");
    }

    @Override
    public String getSeguro( ) throws RemoteException {
        Seguro objSeguro = new Seguro();
        objSeguro.setNombre("Seguro Condor,");
        objSeguro.setCobertura("Pérdida total del vehículo,");
        objSeguro.setBeneficio("Póliza digital,");
        objSeguro.setRequisito("Descargar y llenar formulario “persona natural”,");
        
        return objSeguro.getNombre()+ objSeguro.getBeneficio()+ objSeguro.getCobertura()+ objSeguro.getRequisito();
      
    }
    
}

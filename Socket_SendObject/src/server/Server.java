package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.Seguro;

public class Server {
    private int port = 8081;
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor Socket Iniciado en el puerto " + serverSocket.getLocalPort() + "...");
            while (true) {
            socket = serverSocket.accept();
            System.out.println("Cliente " + socket.getRemoteSocketAddress() + " Conectando al servidor...");

            Seguro s=new Seguro();
            s.setNombre("Seguro Autonomi");
            s.setCobertura("Pérdida total del vehículo");
            s.setBeneficio("Se pagará al beneficiario una indemnización por depreciación del vehículo ");
            s.setRequisito("Aplica únicamente para vehículos livianos particulares hasta 15 años de antigüedad, sin límites de eventos.");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(s);
            objectOutputStream.flush();
            objectOutputStream.close();
            socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server1 = new Server();
    }
}
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws  Exception{

        Scanner sc = new Scanner(System.in);

        while(true){
            // Necesitamos host y puerto, queremos conectarnos al ServerSocket en el puerto 55533
            Socket socket = new Socket("127.0.0.1", 55533);
            System.out.println("Connected!");
            System.out.println("Bienvenido, seleccione una opción:\n" +
                    "1. Transferir dinero\n" +
                    "2. Salir");
            int option = sc.nextInt();

            // obtener el flujo de salida del socket.
            OutputStream outputStream = socket.getOutputStream();
            // crear un flujo de salida de objeto a partir del flujo de salida para que podamos enviar un objeto a través de él
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            // hacer un montón de mensajes para enviar.
            List<Message> messages = new ArrayList<>();
            if(option == 1){
                System.out.println("Ingresa la cuenta: \n");
                String account = sc.next();
                sc.nextLine();
                messages.add(new Message(account));
                System.out.println("Ingresa el monto: \n");
                String value = sc.nextLine();
                messages.add(new Message(value));

                System.out.println("Enviando mensaje al ServerSocket");
                objectOutputStream.writeObject(messages);

                System.out.println("Cerrando socket");
                socket.close();
            } else {
                objectOutputStream.writeObject(messages);
                System.out.println("Adios!!");
                socket.close();
                break;
            }
        }
    }
}

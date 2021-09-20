import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String[] args) throws  Exception{

        // escucha en el puerto especificado
        int port = 55533;
        ServerSocket server = new ServerSocket(port);
        // El servidor siempre esperará a que llegue la conexión
        System.out.println ("El servidor siempre esperará a que llegue la conexión");

        // Si usa varios subprocesos, necesita un grupo de subprocesos para evitar que muchos subprocesos se queden sin recursos cuando la concurrencia es demasiado alta
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        //contador para evitar sobreescribir el documento
        int count = 0;
        while (true) {
            Socket socket = server.accept();
                try {
                    // Después de establecer la conexión, obtenga el flujo de entrada del socket y establezca un búfer para leer
                    InputStream inputStream = socket.getInputStream();
                    // Objeto que recibiremos
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    // Indicamos que su tipo es lista
                    List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
                    //Array donde almacenaremos los datos temporalmente para pasar al txt
                    ArrayList<String> sb = new ArrayList<String>();
                    System.out.println(listOfMessages.size());

                    Document document = new Document();

                    if(count == 0){
                        document.CreateFiles();
                        count +=1;
                        System.out.println(count);
                    }

                    if(listOfMessages.size() != 0) {
                        // Preste atención al formato de codificación especificado, el remitente y el receptor deben estar unificados, se recomienda utilizar UTF-8
                        for (Message msg : listOfMessages) {
                            sb.add(msg.getText());
                        }
                        System.out.println("get message from client: " + sb);

                        document.WriteDocument(sb.get(0), sb.get(1));

                        inputStream.close();
                        socket.close();
                    }else if(listOfMessages.size() == 0){
                        System.out.println("Error");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}

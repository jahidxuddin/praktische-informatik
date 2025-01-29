import socketio.ServerSocket;
import socketio.Socket;

import java.io.IOException;
import java.util.Calendar;

void main() {
    try {
        ServerSocket server = new ServerSocket(1234);
        while (true) {
            Socket client = server.accept();
            new Thread(() -> {
                try {
                    client.write(Calendar.getInstance().getTime() + "\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

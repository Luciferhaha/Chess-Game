package Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {
	/**
	 * Main program that runs the server
	 * @param args: arg0 host, arg1 port
	 * @throws IOException hopefully this is never thrown
	 * since there will be nothing to catch the error
	 */
	public static void main (String[] args) throws IOException
    {

		if (args.length != 2) {
			System.err.println("Usage: java Server.ChessServer host port");
			System.exit(1);
		}
		String host = args[0];
		int port = Integer.parseInt (args[1]);

		@SuppressWarnings("resource")
		ServerSocket serversocket = new ServerSocket();
		serversocket.bind (new InetSocketAddress (host, port));


		for (;;)
		{
			Socket socket = serversocket.accept();
			
		}
	}
}

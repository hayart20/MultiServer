package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import beans.XmlFileDataBean;

/**
 * This class open serversocket by given port, and waiting client request until socket stop.
 * For each request create Threaded.
 * @author hayk
 *
 */
public class MultiThreadedServer implements Runnable{

    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    XmlFileDataBean configBean = null; 

    /**
     * class constructor for init startup variable.
     * @param configBean
     */
    public MultiThreadedServer(XmlFileDataBean configBean){
    	this.configBean = configBean;
        //this.serverPort = configBean.getPort();
    }

    @Override
    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped isStopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            new Thread(
                new WorkerRunnable(
                    clientSocket, configBean)
            ).start();
        }
        System.out.println("Server Stopped.") ;
    }

/**
 * 
 * @return
 */
    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    /**
     * 
     */
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    /**
     * 
     */
    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(configBean.getPort());
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + configBean.getPort(), e);
        }
    }

    
}


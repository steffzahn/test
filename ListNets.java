import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class ListNets {

    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress: %s%s%s\n", inetAddress.getHostAddress(),
                       inetAddress.isLoopbackAddress()?" (loopback)":"",
                       inetAddress.isLinkLocalAddress()?" (linklocal)":"");
        }
        List<InterfaceAddress> ifAddresses = netint.getInterfaceAddresses();
        for (InterfaceAddress ifAddress : ifAddresses) {
            out.printf("InterfaceAddress: %s/%d\n", ifAddress.getAddress(),ifAddress.getNetworkPrefixLength());
            out.printf("Broadcast:        %s\n", ifAddress.getBroadcast());
        }
        out.printf("\n");
     }
}
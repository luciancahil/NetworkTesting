package Main;

public class Main {

    public static void main(String[] args) {
        int input = 784;
        int output = 10;
        int[] hiddenSizes = {526, 268};

        Network network = new Network(input, output, hiddenSizes);
        System.out.println(network.getBiasIndex(0, 10));
    }
}

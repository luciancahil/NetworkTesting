package Main;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {
    @org.junit.jupiter.api.Test
    public void firstTest(){
        assertEquals(1, 1);
    }

    @org.junit.jupiter.api.Test
    public void numLayers(){
        // if you failed this, you don't have the correct number of layers
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(5,
                network.getNumLayers(),
                "You don't have the correct number of layers");
    }

    @org.junit.jupiter.api.Test
    public void numLayersZero(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(2,
                network.getNumLayers(),
                "You don't have the correct number of layers");
    }


    @org.junit.jupiter.api.Test
    public void numBiases(){
        // if you failed this, you don't have the correct number of layers
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(18,
                network.getNumBiases(),
                "You don't have the correct number of Biases");
    }

    @org.junit.jupiter.api.Test
    public void numWeights(){
        // if you failed this, you don't have the correct number of layers
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(116,
                network.getNumWeights(),
                "You don't have the correct number of Weights");
    }

    @org.junit.jupiter.api.Test
    public void numNeurons(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(30, network.getNumNeurons(), "You don't have the correct number of neurons.");
    }

    @org.junit.jupiter.api.Test
    public void numNeuronsInputLayer(){
        // if you failed this, you don't have the correct number of neurons in the input layer
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(12,
                network.getNumNeurons(0),
                "You don't have the correct number of neurons in the input layer");
    }

    @org.junit.jupiter.api.Test
    public void numNeuronsOutputLayer(){
        // if you failed this, you don't have the correct number of neurons in layer 1
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(3,
                network.getNumNeurons(4),
                "You don't have the correct number of neurons in the output layer");
    }

    @org.junit.jupiter.api.Test
    public void numNeuronsHiddenLayers(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        assertEquals(4,
                network.getNumNeurons(1),
                "You don't have the right number of neurons in hidden layer 1");
        assertEquals(5,
                network.getNumNeurons(2),
                "You don't have the right number of neurons in hidden layer 2");
        assertEquals(6,
                network.getNumNeurons(3),
                "You don't have the right number of neurons in a hidden layer 3");
    }

    @org.junit.jupiter.api.Test
    public void numNeuronsNegativeLayer(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        try{
            network.getNumNeurons(-1);
            fail("You needed to throw an IllegalArgumentExceptionError for negative layers at numNeurons" +
                    "for negative layers. " +
                    "You threw nothing");
        }catch(Exception e){
            if(!(e instanceof IllegalArgumentException)){
                fail("You needed to throw an IllegalArgumentExceptionError for negative layers at numNeurons" +
                        "for negative layers." +
                        "You threw the wrong error.");
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void numNeuronsNonexistentLayer(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);

        try{
            network.getNumNeurons(10);
            fail("You needed to throw an IllegalArgumentExceptionError for negative layers at numNeurons" +
                    "for layers that don't exist. " +
                    "You threw nothing");
        }catch(Exception e){
            if(!(e instanceof IllegalArgumentException)){
                fail("You needed to throw an IllegalArgumentExceptionError for negative layers at numNeurons." +
                        "for layers that don't exist" +
                        "You threw the wrong error.");
            }
        }
    }
    // ENDTEST END OF TESTS FOR NUMBER

    // STARTEST START OF INDEX TESTS
    @org.junit.jupiter.api.Test
    public void checkBiases(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};
        double value = Math.random() * 100;
        int layers;

        Network network = new Network(input, output, hiddenSizes);

        layers = 5;

        for(int layer = 1; layer < layers; layer++){
            int neurons = network.getNumNeurons(layer);
            for(int index = 0; index < neurons; index++){
                network.setBias(layer, index, value);
                assertEquals(value,
                        network.getBias(layer, index),
                        "You did not correctly set the value of B(" + layer + ", " + index +".");

                value = Math.random() * 100;
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void checkBiasesInputLayer(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);
        try{
            network.getBias(0, 0);
            fail("Expected an Illegal Argument Exception when asking for an input layer bias." +
                    "Got no error.");
        }catch(Exception e){
            if(!(e instanceof IllegalArgumentException)){
                fail("Expected an Illegal Argument Exception when asking for an input layer bias." +
                        "Got the wrong error.");
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void getBiasesNullLayer(){
        int input = 12;
        int output = 3;
        int[] hiddenSizes = {4, 5, 6};

        Network network = new Network(input, output, hiddenSizes);
        try{
            network.getBias(-1, 0);
            fail("Expected an Illegal Argument Exception when asking for a nonexistant layer bias." +
                    "Got no error.");
        }catch(Exception e){
            if(!(e instanceof IllegalArgumentException)){
                fail("Expected an Illegal Argument Exception when asking for an nonexistant layer bias." +
                        "Got the wrong error.");
            }
        }
    }

    // get bias layer 1
    // set bias layer 1
    // set bias nonexistant layer
    // set bias existant layer, non existing index
    // get bias existing layer, nonexistant index

    // check every weight
    // check get nonexistant source
    // check get nonexistant destination
    // check set nonexistant source
    // check set nonexistant destination


    // check every weighted sum
    // check get nonexistant layer
    // go with the flow
}
package Main;

public class Network {
    private final double[] weights;

    private final double[] biases;

    private final double[] weightedSums;

    private final int[] layerSizes;

    public Network(int inputSize, int outputSize, int[] hiddenSizes){
        int numWeights;
        int numBiases;
        int numNeurons;
        int numLayers;
        int numHidden = hiddenSizes.length;

        // setting the layer sizes
        numLayers = numHidden + 2;
        layerSizes = new int[numLayers];

        // setting the layersize array values
        layerSizes[0] = inputSize;
        for(int i = 0; i < numHidden; i++){
            layerSizes[i + 1] = hiddenSizes[i];
        }
        layerSizes[numLayers - 1] = outputSize;

        // setting the number of neurons
        numNeurons = inputSize + outputSize;

        for(int i = 0; i < numHidden; i++){
            numNeurons += hiddenSizes[i];
        }
        weightedSums = new double[numNeurons];


        // setting the number of biases;
        numBiases = numNeurons - inputSize;
        biases = new double[numBiases];

        // setting the number of weights
        if(numHidden != 0) {
            numWeights = inputSize * hiddenSizes[0];
            for (int i = 1; i < numHidden; i++) {
                numWeights += hiddenSizes[i] * hiddenSizes[i - 1];
            }
            numWeights += hiddenSizes[numHidden - 1] * outputSize;
        }else{
            numWeights = inputSize * outputSize;
        }

        weights = new double[numWeights];
    }

    /**
     * returns the number of layers in the neural network
     * @return number of layers in the neural network
     */
    public int getNumLayers(){
        return layerSizes.length;
    }

    public int getNumBiases(){
        return biases.length;
    }

    public int getNumWeights(){
        return weights.length;
    }

    public int getNumNeurons(){
        return weightedSums.length;
    }

    public int getNumNeurons(int layer){
        validateLayer(layer);

        return layerSizes[layer];
    }


    public double getBias(int layer, int index){

        return biases[getBiasIndex(layer, index)];
    }

    public double getWeight(int destLayer, int sourceIndex, int destIndex){
        validateWeight(destLayer, sourceIndex, destIndex);

        return 0;
    }

    public double getWeightedSum(int layer, int index){

        return 0;
    }


    public void setBias(int layer, int index, double value){
        biases[getBiasIndex(layer, index)] = value;
    }

    public void setWeights(int destLayer, int sourceIndex, int destIndex){

    }

    public void setWeightedSums(int layer, int index){

    }

    public int getBiasIndex(int layer, int index){
        validateBias(layer, index);
        int biasIndex = 0;

        for(int i = 1; i < layer; i++){
            biasIndex += layerSizes[i];
        }

        biasIndex += index;

        return biasIndex;
    }

    private void validateLayer(int layer){
        if (layer < 0) {
            throw new IllegalArgumentException("There are no layers before layer 0");
        }

        if(layer >= getNumLayers()){
            throw new IllegalArgumentException("There are no layers with index" + layer);
        }
    }

    // fails if a neuron doesn't exist
    private void validateNeuron(int layer, int index){
        validateLayer(layer);

        if (index < 0 || index >= layerSizes[layer]){
            throw new IllegalArgumentException("Neuron " + index + " on layer " + layer + " does not exist");
        }
    }

    private  void validateBias(int layer, int index){
        validateNeuron(layer, index);

        if(layer == 0){
            throw new IllegalArgumentException("There are no biases on the input layer");
        }
    }

    private void validateWeight(int destLayer, int sourceIndex, int destIndex) {
        validateNeuron(destLayer - 1, sourceIndex);
        validateNeuron(destLayer, destIndex);
    }
}

package neuroNet.limeth;

import neuroNet.limeth.network.NeuralLayer;
import neuroNet.limeth.network.NeuralNetwork;
import neuroNet.limeth.network.functions.ActivationFunction;
import neuroNet.limeth.network.neurons.BiasNeuron;
import neuroNet.limeth.network.neurons.InputNeuron;
import neuroNet.limeth.network.neurons.OutputNeuron;

public class Main
{
	public static void main(String[] args)
	{
		//NeuralNetwork network = newXORNetwork();
		
		System.out.println(ActivationFunction.SIGMOID.derive(1.13) * 0.25);
	}
	
	public static NeuralNetwork newXORNetwork()
	{
		NeuralNetwork network = new NeuralNetwork(ActivationFunction.SIGMOID, 1);
		
		//Input layer
		NeuralLayer inputLayer = new NeuralLayer(network);
		new InputNeuron(inputLayer)
		{
			@Override
			public double getInput()
			{
				return 0;
			}
		};
		new InputNeuron(inputLayer)
		{
			@Override
			public double getInput()
			{
				return 0;
			}
		};
		new BiasNeuron(inputLayer);
		
		//Hidden layers
	//	NeuralLayer.createSimpleHiddenLayer(network, null, 2, true);
		
		//Output layer
		NeuralLayer outputLayer = new NeuralLayer(network);
		new OutputNeuron(outputLayer);
		
		return network;
	}
}

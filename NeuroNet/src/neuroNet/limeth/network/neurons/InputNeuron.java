package neuroNet.limeth.network.neurons;

import neuroNet.limeth.network.NeuralLayer;
import neuroNet.limeth.network.NeuralNetwork;
import neuroNet.limeth.network.functions.ActivationFunction;

public abstract class InputNeuron extends Neuron
{
	public InputNeuron(NeuralLayer layer)
	{
		super(layer);
	}

	public abstract double getInput();
	
	@Override
	public InputNeuron register()
	{
		NeuralLayer layer = getLayer();
		
		if(!layer.isLast())
		{
			NeuralLayer nextLayer = layer.getNextLayer();
			
			for(Neuron neuron : nextLayer)
				if(neuron.canConnect(this))
					connect(neuron);
		}

		layer.add(this);
		
		return this;
	}
	
	@Override
	public double getOutput()
	{
		NeuralNetwork network = getNetwork();
		ActivationFunction afc = network.getActivationFunction();
		double input = getInput();
		
		return afc.getValue(input);
	}
	
	@Override
	public boolean canConnect(Neuron neuron)
	{
		NeuralNetwork network = getNetwork();
		NeuralLayer my = getLayer();
		NeuralLayer its = neuron.getLayer();
		int myIndex = network.indexOf(my);
		int itsIndex = network.indexOf(its);
		
		return myIndex < itsIndex;
	}
}

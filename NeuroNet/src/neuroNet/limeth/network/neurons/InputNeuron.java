package neuroNet.limeth.network.neurons;

import neuroNet.limeth.NeuralLayer;
import neuroNet.limeth.NeuralNetwork;
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
			
			for(INeuron neuron : nextLayer)
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
	public boolean canConnect(INeuron neuron)
	{
		NeuralNetwork network = getNetwork();
		NeuralLayer my = getLayer();
		NeuralLayer its = neuron.getLayer();
		int myIndex = network.indexOf(my);
		int itsIndex = network.indexOf(its);
		
		return myIndex < itsIndex;
	}
}

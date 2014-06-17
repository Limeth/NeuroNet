package neuroNet.limeth.network.neurons;

import neuroNet.limeth.network.NeuralLayer;
import neuroNet.limeth.network.NeuralNetwork;

public class HiddenNeuron extends Neuron
{
	public HiddenNeuron(NeuralLayer layer)
	{
		super(layer);
	}
	
	@Override
	public HiddenNeuron register()
	{
		NeuralLayer layer = getLayer();
		
		if(!layer.isFirst())
		{
			NeuralLayer previousLayer = layer.getPreviousLayer();
			
			for(Neuron neuron : previousLayer)
				if(neuron.canConnect(this))
					neuron.connect(this);
		}
		
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
	public boolean canConnect(Neuron neuron)
	{
		NeuralNetwork network = getNetwork();
		NeuralLayer my = getLayer();
		NeuralLayer its = neuron.getLayer();
		int myIndex = network.indexOf(my);
		int itsIndex = network.indexOf(its);
		
		return myIndex != itsIndex;
	}
}

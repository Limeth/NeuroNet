package neuroNet.limeth.network.neurons;

import neuroNet.limeth.NeuralLayer;
import neuroNet.limeth.NeuralNetwork;

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
			
			for(INeuron neuron : previousLayer)
				if(neuron.canConnect(this))
					neuron.connect(this);
		}
		
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
	public boolean canConnect(INeuron neuron)
	{
		NeuralNetwork network = getNetwork();
		NeuralLayer my = getLayer();
		NeuralLayer its = neuron.getLayer();
		int myIndex = network.indexOf(my);
		int itsIndex = network.indexOf(its);
		
		return myIndex != itsIndex;
	}
}

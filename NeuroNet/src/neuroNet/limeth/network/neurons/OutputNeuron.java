package neuroNet.limeth.network.neurons;

import neuroNet.limeth.NeuralLayer;
import neuroNet.limeth.NeuralNetwork;

public class OutputNeuron extends Neuron
{
	public OutputNeuron(NeuralLayer layer)
	{
		super(layer);
	}
	
	@Override
	public OutputNeuron register()
	{
		NeuralLayer layer = getLayer();
		
		if(!layer.isFirst())
		{
			NeuralLayer previousLayer = layer.getPreviousLayer();
			
			for(INeuron neuron : previousLayer)
				if(neuron.canConnect(this))
					neuron.connect(this);
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
		
		return itsIndex < myIndex;
	}
}

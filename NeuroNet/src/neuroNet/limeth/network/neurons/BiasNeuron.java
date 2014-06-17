package neuroNet.limeth.network.neurons;

import neuroNet.limeth.network.NeuralLayer;
import neuroNet.limeth.network.NeuralNetwork;


public class BiasNeuron extends Neuron
{
	public double value;
	
	public BiasNeuron(NeuralLayer layer, double value)
	{
		super(layer);
		
		this.value = value;
	}
	
	public BiasNeuron(NeuralLayer layer)
	{
		this(layer, 1);
	}
	
	@Override
	public BiasNeuron register()
	{
		NeuralLayer layer = getLayer();
		
		if(!layer.isLast())
		{
			NeuralLayer nextLayer = layer.getNextLayer();
			
			for(Neuron neuron : nextLayer)
				connect(neuron);
		}
		
		layer.add(this);
		
		return this;
	}
	
	@Override
	public double getOutput()
	{
		return value;
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}
	
	@Override
	public boolean canConnect(Neuron neuron)
	{
		NeuralNetwork network = getNetwork();
		NeuralLayer my = getLayer();
		NeuralLayer its = neuron.getLayer();
		int myIndex = network.indexOf(my);
		int itsIndex = network.indexOf(its);
		
		return itsIndex > myIndex;
	}
}

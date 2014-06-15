package neuroNet.limeth.network.neurons;

import neuroNet.limeth.NeuralConnection;
import neuroNet.limeth.NeuralConnectionSet;
import neuroNet.limeth.NeuralLayer;
import neuroNet.limeth.NeuralNetwork;
import neuroNet.limeth.network.functions.ActivationFunction;


public abstract class Neuron implements INeuron
{
	private final NeuralLayer layer;
	private Double lazySum, lazyOutput;

	public Neuron(NeuralLayer layer)
	{
		if(layer == null)
			throw new IllegalArgumentException("The layer cannot be null!");
		
		this.layer = layer;
	}
	
	public static boolean canConnect(INeuron first, INeuron second)
	{
		return first.canConnect(second) && second.canConnect(first);
	}
	
	public void resetOutput()
	{
		lazySum = null;
		lazyOutput = null;
	}
	
	@Override
	public double getSum()
	{
		return lazySum != null ? lazySum : calculateSum();
	}
	
	@Override
	public double getOutput()
	{
		return lazyOutput != null ? lazyOutput : calculateOutput();
	}
	
	private double calculateSum()
	{
		NeuralNetwork network = getNetwork();
		NeuralConnectionSet globalSet = network.getConnectionSet();
		NeuralConnectionSet inputSet = globalSet.getInput(this);
		
		double value = 0;
		
		for(NeuralConnection conn : inputSet)
		{
			double weight = conn.getWeight();
			INeuron other = conn.getOther(this);
			double otherValue = other.getOutput();
			
			value += otherValue * weight;
		}
		
		return lazySum = value;
	}
	
	private double calculateOutput()
	{
		NeuralNetwork network = getNetwork();
		ActivationFunction afc = network.getActivationFunction();
		double sum = getSum();
		
		return lazyOutput = afc.getValue(sum);
	}
	
	@Override
	public boolean connect(INeuron neuron, double weight)
	{
		if(!canConnect(this, neuron))
			throw new IllegalArgumentException("Neurons " + this + " and " + neuron + " cannot be connected!");
		
		NeuralNetwork network = getNetwork();
		NeuralConnectionSet set = network.getConnectionSet();
		NeuralConnection conn = new NeuralConnection(this, neuron, weight);
		
		return set.add(conn);
	}
	
	@Override
	public boolean connect(INeuron neuron)
	{
		NeuralNetwork network = getNetwork();
		double weightRange = network.getWeightRange();
		double weight = (Math.random() * 2 - 1) * weightRange;
		
		return connect(neuron, weight);
	}
	
	public NeuralLayer getLayer()
	{
		return layer;
	}
	
	public NeuralNetwork getNetwork()
	{
		return layer.getNetwork();
	}
	
	@Override
	public String toString()
	{
		Class<? extends Neuron> clazz = getClass();
		NeuralNetwork network = getNetwork();
		int index = network.indexOf(layer);
		
		return (clazz.isAnonymousClass() ? clazz.getTypeName() : clazz.getSimpleName()) + " (layer " + index + ")";
	}
}

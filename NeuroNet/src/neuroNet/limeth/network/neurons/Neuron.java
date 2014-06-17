package neuroNet.limeth.network.neurons;

import java.util.function.Predicate;

import neuroNet.limeth.network.NeuralConnection;
import neuroNet.limeth.network.NeuralConnectionSet;
import neuroNet.limeth.network.NeuralLayer;
import neuroNet.limeth.network.NeuralNetwork;
import neuroNet.limeth.network.functions.ActivationFunction;


public abstract class Neuron
{
	private final NeuralLayer layer;
	private final NeuralConnectionSet connectionSet;
	private Double lazySum, lazyOutput;
	
	public Neuron(NeuralLayer layer)
	{
		if(layer == null)
			throw new IllegalArgumentException("The layer cannot be null!");
		
		this.layer = layer;
		connectionSet = new NeuralConnectionSet();
	}
	
	public abstract Neuron register();
	public abstract boolean canConnect(Neuron second);
	
	public static boolean canConnect(Neuron first, Neuron second)
	{
		return first.canConnect(second) && second.canConnect(first);
	}
	
	public void resetOutput()
	{
		lazySum = null;
		lazyOutput = null;
	}
	
	public double getSum()
	{
		return lazySum != null ? lazySum : calculateSum();
	}
	
	public double getOutput()
	{
		return lazyOutput != null ? lazyOutput : calculateOutput();
	}
	
	private double calculateSum()
	{
		NeuralConnectionSet inputSet = getConnections(NeuralConnectionSet.isOutput(this));
		
		double value = 0;
		
		for(NeuralConnection conn : inputSet)
		{
			double weight = conn.getWeight();
			Neuron other = conn.getOther(this);
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
	
	public boolean connect(Neuron neuron, double weight)
	{
		if(!canConnect(this, neuron))
			throw new IllegalArgumentException("Neurons " + this + " and " + neuron + " cannot be connected!");
		
		NeuralConnection conn = new NeuralConnection(this, neuron, weight);
		
		return connectionSet.add(conn) & neuron.connectionSet.add(conn);
	}
	
	public boolean connect(Neuron neuron)
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
	
	public NeuralConnectionSet getConnections()
	{
		return (NeuralConnectionSet) connectionSet.clone();
	}
	
	public NeuralConnectionSet getConnections(Predicate<? super NeuralConnection> predicate)
	{
		return getConnections().getAll(predicate);
	}
}

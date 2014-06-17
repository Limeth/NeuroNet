package neuroNet.limeth.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import neuroNet.limeth.network.functions.ActivationFunction;
import neuroNet.limeth.network.neurons.Neuron;

@SuppressWarnings("serial")
public class NeuralNetwork extends ArrayList<NeuralLayer>
{
	private ActivationFunction activationFunction;
	private double weightRange;
	
	public NeuralNetwork(ActivationFunction activationFunction, double weightRange)
	{
		super();
		
		if(activationFunction == null)
			throw new IllegalArgumentException("The activation function cannot be null!");
		else if(weightRange <= 0)
			throw new IllegalArgumentException("The weight range must be positive!");
		
		this.weightRange = weightRange;
		this.activationFunction = activationFunction;
	}
	
	public Set<NeuralLayer> getLayers(Predicate<? super NeuralLayer> predicate)
	{
		return stream().filter(predicate).collect(Collectors.toSet());
	}
	
	public ArrayList<Neuron> getNeurons(Predicate<? super Neuron> predicate)
	{
		ArrayList<Neuron> set = new ArrayList<Neuron>();
		
		for(NeuralLayer layer : this)
			set.addAll(layer.get(predicate));
		
		return set;
	}
	
	public ArrayList<Neuron> getNeurons()
	{
		ArrayList<Neuron> set = new ArrayList<Neuron>();
		
		for(NeuralLayer layer : this)
			set.addAll(layer);
		
		return set;
	}
	
	public NeuralConnectionSet getConnections()
	{
		NeuralConnectionSet set = new NeuralConnectionSet();
		ArrayList<Neuron> neurons = getNeurons();
		
		for(Neuron neuron : neurons)
			set.addAll(neuron.getConnections());
		
		return set;
	}
	
	public NeuralConnectionSet getConnections(Predicate<? super NeuralConnection> predicate)
	{
		NeuralConnectionSet set = new NeuralConnectionSet();
		ArrayList<Neuron> neurons = getNeurons();
		
		for(Neuron neuron : neurons)
			set.addAll(neuron.getConnections(predicate));
		
		return set;
	}
	
	public void resetOutputs()
	{
		for(Neuron neuron : getNeurons())
		{
			neuron.resetOutput();
		}
	}

	@Override
	public void add(int index, NeuralLayer value)
	{
		if(!canBeAdded().test(value))
			return;
		
		super.add(index, value);
	}
	
	@Override
	public boolean add(NeuralLayer value)
	{
		if(!canBeAdded().test(value))
			return false;
		
		return super.add(value);
	}
	
	@Override
	public boolean addAll(Collection<? extends NeuralLayer> coll)
	{
		coll = coll.stream().filter(canBeAdded()).collect(Collectors.toList());
		
		return super.addAll(coll);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends NeuralLayer> coll)
	{
		coll = coll.stream().filter(canBeAdded()).collect(Collectors.toList());
		
		return super.addAll(index, coll);
	}
	
	private Predicate<NeuralLayer> canBeAdded()
	{
		return value -> { return value != null && !containsStrict(value); };
	}
	
	public boolean containsStrict(NeuralLayer layer)
	{
		for(NeuralLayer current : this)
			if(current == layer)
				return true;
		
		return false;
	}

	public ActivationFunction getActivationFunction()
	{
		return activationFunction;
	}

	public void setActivationFunction(ActivationFunction activationFunction)
	{
		if(activationFunction == null)
			throw new IllegalArgumentException("The activation function cannot be null!");
		
		this.activationFunction = activationFunction;
	}

	public double getWeightRange()
	{
		return weightRange;
	}

	public void setWeightRange(double weightRange)
	{
		if(weightRange <= 0)
			throw new IllegalArgumentException("The weight range must be positive!");
		
		this.weightRange = weightRange;
	}
}

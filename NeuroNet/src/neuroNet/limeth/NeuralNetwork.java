package neuroNet.limeth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import neuroNet.limeth.network.functions.ActivationFunction;
import neuroNet.limeth.network.neurons.INeuron;

@SuppressWarnings("serial")
public class NeuralNetwork extends ArrayList<NeuralLayer>
{
	private ActivationFunction activationFunction;
	private double weightRange;
	private final NeuralConnectionSet connectionSet;
	
	public NeuralNetwork(ActivationFunction activationFunction, double weightRange, NeuralConnectionSet connectionSet)
	{
		super();
		
		if(activationFunction == null)
			throw new IllegalArgumentException("The activation function cannot be null!");
		else if(weightRange <= 0)
			throw new IllegalArgumentException("The weight range must be positive!");
		else if(connectionSet == null)
			throw new IllegalArgumentException("The connection set cannot be null!");
		
		this.weightRange = weightRange;
		this.activationFunction = activationFunction;
		this.connectionSet = connectionSet;
	}
	
	public NeuralNetwork(ActivationFunction activationFunction, double weightRange)
	{
		this(activationFunction, weightRange, new NeuralConnectionSet());
	}
	
	public Set<NeuralLayer> getLayers(Predicate<? super NeuralLayer> predicate)
	{
		return stream().filter(predicate).collect(Collectors.toSet());
	}
	
	public ArrayList<INeuron> getNeurons(Predicate<? super INeuron> predicate)
	{
		ArrayList<INeuron> set = new ArrayList<INeuron>();
		
		for(NeuralLayer layer : this)
			set.addAll(layer.getAll(predicate));
		
		return set;
	}
	
	public ArrayList<INeuron> getNeurons()
	{
		ArrayList<INeuron> set = new ArrayList<INeuron>();
		
		for(NeuralLayer layer : this)
			set.addAll(layer);
		
		return set;
	}
	
	public void resetOutputs()
	{
		for(INeuron neuron : getNeurons())
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

	public NeuralConnectionSet getConnectionSet()
	{
		return connectionSet;
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

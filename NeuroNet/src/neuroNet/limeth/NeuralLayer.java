package neuroNet.limeth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import neuroNet.limeth.network.neurons.BiasNeuron;
import neuroNet.limeth.network.neurons.HiddenNeuron;
import neuroNet.limeth.network.neurons.INeuron;

@SuppressWarnings("serial")
public class NeuralLayer extends ArrayList<INeuron>
{
	private final NeuralNetwork network;
	
	public NeuralLayer(NeuralNetwork network)
	{
		super();
		
		if(network == null)
			throw new IllegalArgumentException("The network cannot be null!");
		
		this.network = network;
	}
	
	public NeuralLayer register(Integer index)
	{
		if(index != null)
			network.add(index, this);
		else
			network.add(this);
		
		return this;
	}
	
	public NeuralLayer register()
	{
		return register(null);
	}
	
	public static NeuralLayer createSimpleHiddenLayer(NeuralNetwork network, int neurons, boolean biasNeuron)
	{
		NeuralLayer layer = new NeuralLayer(network);
		
		for(int i = 0; i < neurons; i++)
			new HiddenNeuron(layer);
		
		if(biasNeuron)
			new BiasNeuron(layer);
		
		return layer;
	}
	
	public NeuralLayer getPreviousLayer()
	{
		int index = network.indexOf(this);
		
		if(index > 0)
			return network.get(index - 1);
		else
			return null;
	}
	
	public NeuralLayer getNextLayer()
	{
		int index = network.indexOf(this);
		
		if(index < network.size() - 1)
			return network.get(index + 1);
		else
			return null;
	}
	
	public boolean isFirst()
	{
		return network.indexOf(this) <= 0;
	}
	
	public boolean isLast()
	{
		return network.indexOf(this) >= network.size() - 1;
	}
	
	public Set<INeuron> getAll(Predicate<? super INeuron> predicate)
	{
		return stream().filter(predicate).collect(Collectors.toSet());
	}
	
	@Override
	public boolean add(INeuron value)
	{
		if(value == null)
			return false;
		
		return super.add(value);
	}
	
	@Override
	public void add(int index, INeuron value)
	{
		if(value == null)
			return;
		
		super.add(index, value);
	}
	
	@Override
	public boolean addAll(Collection<? extends INeuron> coll)
	{
		coll = coll.stream().filter(value -> { return value != null; }).collect(Collectors.toList());
		
		return super.addAll(coll);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends INeuron> coll)
	{
		coll = coll.stream().filter(value -> { return value != null; }).collect(Collectors.toList());
		
		return super.addAll(index, coll);
	}

	public NeuralNetwork getNetwork()
	{
		return network;
	}
}

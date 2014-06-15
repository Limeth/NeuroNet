package neuroNet.limeth;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import neuroNet.limeth.network.neurons.INeuron;

@SuppressWarnings("serial")
public class NeuralConnectionSet extends HashSet<NeuralConnection>
{
	public NeuralConnectionSet getInput(INeuron neuron)
	{
		return getAll(conn -> { return isRelated(neuron).test(conn) && conn.getTo().equals(neuron); });
	}
	
	public NeuralConnectionSet getOutput(INeuron neuron)
	{
		return getAll(conn -> { return isRelated(neuron).test(conn) && conn.getFrom().equals(neuron); });
	}
	
	public NeuralConnectionSet getRelated(INeuron... neurons)
	{
		return getAll(isRelated(neurons));
	}
	
	public NeuralConnection getRelatedConnection(INeuron... neurons)
	{
		NeuralConnectionSet set = getRelated(neurons);
		
		if(set.size() <= 0)
			return null;
		else if(set.size() > 1)
			throw new RuntimeException("Found more than one related connections for given neurons: " + Arrays.toString(neurons));
		
		return set.stream().findAny().get();
	}
	
	public NeuralConnectionSet getAll(Predicate<? super NeuralConnection> predicate)
	{
		return stream().filter(predicate).collect(Collectors.toCollection(NeuralConnectionSet::new));
	}
	
	public static Predicate<? super NeuralConnection> isRelated(INeuron... neurons)
	{
		return conn -> { return conn.contains(neurons); };
	}
	
	@Override
	public boolean add(NeuralConnection value)
	{
		if(value == null)
			return false;
		
		return super.add(value);
	}
	
	@Override
	public boolean addAll(Collection<? extends NeuralConnection> coll)
	{
		boolean returnValue = false;
		
		for(NeuralConnection value : coll)
			if(add(value) && !returnValue)
				returnValue = true;
		
		return returnValue;
	}
}

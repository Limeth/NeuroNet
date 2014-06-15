package neuroNet.limeth;

import neuroNet.limeth.network.neurons.INeuron;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

public class NeuralConnection
{
	private final INeuron from, to;
	private double weight;
	
	public NeuralConnection(INeuron from, INeuron to, double weight)
	{
		if(from == null || to == null)
			throw new IllegalArgumentException("The neurons cannot be null!");
		else if(!from.getNetwork().equals(to.getNetwork()))
			throw new IllegalArgumentException("The neurons must be in the same network!");
		else if(from.equals(to))
			throw new IllegalArgumentException("The neurons cannot be the same!");
		
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public INeuron getOther(INeuron neuron)
	{
		if(neuron == null)
			throw new IllegalAnnotationException("The neuron cannot be null!");
		else if(neuron.equals(from))
			return to;
		else if(neuron.equals(to))
			return from;
		else
			throw new IllegalArgumentException("Invalid neuron.");
	}
	
	public boolean contains(INeuron... neurons)
	{
		for(INeuron neuron : neurons)
			if(!from.equals(neuron) && !to.equals(neuron))
				return false;
		
		return true;
	}
	
	public double addWeight(double increase)
	{
		return weight += increase;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public INeuron getFrom()
	{
		return from;
	}

	public INeuron getTo()
	{
		return to;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object instanceof NeuralConnection)
		{
			NeuralConnection conn = (NeuralConnection) object;
			
			if(conn.from.equals(from) && conn.to.equals(to))
				return true;
		}
		
		return super.equals(object);
	}
}

package neuroNet.limeth.network;

import neuroNet.limeth.network.neurons.Neuron;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

public class NeuralConnection
{
	private final Neuron from, to;
	private double weight;
	
	public NeuralConnection(Neuron from, Neuron to, double weight)
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
	
	public Neuron getOther(Neuron neuron)
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
	
	public boolean contains(Neuron... neurons)
	{
		for(Neuron neuron : neurons)
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

	public Neuron getFrom()
	{
		return from;
	}

	public Neuron getTo()
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

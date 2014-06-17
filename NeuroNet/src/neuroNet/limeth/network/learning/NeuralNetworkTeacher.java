package neuroNet.limeth.network.learning;

import java.util.ArrayList;

import neuroNet.limeth.network.NeuralNetwork;
import neuroNet.limeth.network.functions.ErrorFunction;
import neuroNet.limeth.network.neurons.InputNeuron;
import neuroNet.limeth.network.neurons.OutputNeuron;

public class NeuralNetworkTeacher
{
	private final NeuralNetwork network;
	private final ArrayList<InputNeuron> inputNeurons = new ArrayList<InputNeuron>();
	private final ArrayList<OutputNeuron> outputNeurons = new ArrayList<OutputNeuron>();
	private ErrorFunction errorFunction;
	private TeachingMaterials teachingMaterials;
	private int currentIndex;
	private double[][] achievedOutputs;
	private boolean validated;
	
	public NeuralNetworkTeacher(NeuralNetwork network, ErrorFunction errorFunction)
	{
		if(network == null)
			throw new IllegalArgumentException("The network cannot be null!");
		else if(errorFunction == null)
			throw new IllegalArgumentException("The error function cannot be null!");
		
		this.network = network;
		this.setErrorFunction(errorFunction);
	}
	
	public double getInput(int index)
	{
		TeachingMaterial material = getCurrentMaterial();
		
		return material.getInput(index);
	}
	
	public NeuralNetwork getNetwork()
	{
		return network;
	}

	public TeachingMaterial getCurrentMaterial()
	{
		return teachingMaterials.get(currentIndex);
	}
	
	public void teach(TeachingMaterials materials)
	{
		teachingMaterials = materials;
		achievedOutputs = new double[materials.size()][];
		
		for(currentIndex = 0; currentIndex < materials.size(); currentIndex++)
			teach();
	}
	
	public void teach()
	{
		validate();
		
		TeachingMaterial material = getCurrentMaterial();
		
		
	}
	
	private void validate()
	{
		if(validated)
			return;
		
		if(inputNeurons.size() <= 0)
			throw new RuntimeException("No input neurons set!");
		else if(outputNeurons.size() <= 0)
			throw new RuntimeException("No output neurons set!");
	}
	
	public void clearInputNeurons()
	{
		inputNeurons.clear();
		
		validated = false;
	}
	
	public void clearOutputNeurons()
	{
		outputNeurons.clear();
		
		validated = false;
	}
	
	public void addInputNeurons(InputNeuron... neurons)
	{
		for(InputNeuron neuron : neurons)
			inputNeurons.add(neuron);
		
		validated = false;
	}
	
	public void addOutputNeurons(OutputNeuron... neurons)
	{
		for(OutputNeuron neuron : neurons)
			outputNeurons.add(neuron);
		
		validated = false;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<InputNeuron> getInputNeurons()
	{
		return (ArrayList<InputNeuron>) inputNeurons.clone();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<OutputNeuron> getOutputNeurons()
	{
		return (ArrayList<OutputNeuron>) outputNeurons.clone();
	}

	public ErrorFunction getErrorFunction()
	{
		return errorFunction;
	}

	public void setErrorFunction(ErrorFunction errorFunction)
	{
		if(errorFunction == null)
			throw new IllegalArgumentException("The error function cannot be null!");
		
		this.errorFunction = errorFunction;
	}
}

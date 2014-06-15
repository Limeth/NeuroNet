package neuroNet.limeth.network.learning;

public class TeachingMaterial
{
	public double[] inputs;
	public double[] outputs;
	
	public TeachingMaterial(double[] inputs, double[] outputs)
	{
		this.inputs = inputs;
		this.outputs = outputs;
	}
	
	public double getInput(int index)
	{
		return inputs[index];
	}

	public double[] getInputs()
	{
		return inputs;
	}
	
	public void setInput(int index, double value)
	{
		inputs[index] = value;
	}

	public void setInputs(double[] inputs)
	{
		this.inputs = inputs;
	}
	
	public double getOutput(int index)
	{
		return outputs[index];
	}

	public double[] getOutputs()
	{
		return outputs;
	}
	
	public void setOutput(int index, double value)
	{
		outputs[index] = value;
	}

	public void setOutputs(double[] outputs)
	{
		this.outputs = outputs;
	}
}

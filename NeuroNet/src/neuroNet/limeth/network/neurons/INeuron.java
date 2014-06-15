package neuroNet.limeth.network.neurons;

import neuroNet.limeth.NeuralLayer;
import neuroNet.limeth.NeuralNetwork;

public interface INeuron
{
	public INeuron register();
	public NeuralLayer getLayer();
	public NeuralNetwork getNetwork();
	public boolean canConnect(INeuron neuron);
	public boolean connect(INeuron neuron, double weight);
	public boolean connect(INeuron neuron);
	public void resetOutput();
	public double getSum();
	public double getOutput();
}

package neuroNet.limeth.network.functions;

public enum ActivationFunction
{
	LINEAR
	{
		@Override
		public double getValue(double value)
		{
			return value;
		}

		@Override
		public double derive(double value)
		{
			throw new UnsupportedOperationException();//TODO
		}
	},
	SIGMOID
	{
		@Override
		public double getValue(double value)
		{
			return 1.0 / (1.0 + Math.exp(-1 * value));
		}

		@Override
		public double derive(double value)
		{
			return getValue(value) * (1 - getValue(value));
		}
	},
	TANH
	{
		@Override
		public double getValue(double value)
		{
			return Math.tanh(value);
		}

		@Override
		public double derive(double value)
		{
			throw new UnsupportedOperationException();//TODO
		}
	};
	
	public abstract double getValue(double value);
	public abstract double derive(double value);
}

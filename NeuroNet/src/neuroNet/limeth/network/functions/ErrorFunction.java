package neuroNet.limeth.network.functions;

public enum ErrorFunction
{
	MEAN_SQUARED
	{
		@Override
		public double getValue(double[] idealValues, double[] achievedValues)
		{
			int length = idealValues.length < achievedValues.length ? idealValues.length : achievedValues.length;
			double value = 0;
			
			for(int i = 0; i < length; i++)
				value += getValue(idealValues[i], achievedValues[i]);
			
			return value / length;
		}
		
		@Override
		public double getValue(double idealValue, double achievedValue)
		{
			return Math.pow(idealValue - achievedValue, 2);
		}
	},
	ROOT_MEAN_SQUARED
	{
		@Override
		public double getValue(double[] idealValues, double[] achievedValues)
		{
			return Math.sqrt(MEAN_SQUARED.getValue(idealValues, achievedValues));
		}
		
		@Override
		public double getValue(double idealValue, double achievedValue)
		{
			return MEAN_SQUARED.getValue(idealValue, achievedValue);
		}
	},
	ARC_TANGENT
	{
		@Override
		public double getValue(double[] idealValues, double[] achievedValues)
		{
			int length = idealValues.length < achievedValues.length ? idealValues.length : achievedValues.length;
			double value = 0;
			
			for(int i = 0; i < length; i++)
				value += getValue(idealValues[i], achievedValues[i]);
			
			return value / length;
		}
		
		@Override
		public double getValue(double idealValue, double achievedValue)
		{
			return Math.pow(Math.atan(idealValue - achievedValue), 2);
		}
	};
	
	public abstract double getValue(double[] idealValues, double[] achievedValues);
	public abstract double getValue(double idealValue, double achievedValue);
}

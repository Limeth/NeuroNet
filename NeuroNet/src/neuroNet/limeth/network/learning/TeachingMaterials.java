package neuroNet.limeth.network.learning;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class TeachingMaterials extends ArrayList<TeachingMaterial>
{
	@Override
	public boolean add(TeachingMaterial value)
	{
		if(value == null)
			return false;
		
		return super.add(value);
	}
	
	@Override
	public boolean addAll(Collection<? extends TeachingMaterial> coll)
	{
		boolean returnValue = false;
		
		for(TeachingMaterial value : coll)
			if(add(value) && !returnValue)
				returnValue = true;
		
		return returnValue;
	}
}

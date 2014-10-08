/* The  makes sure that all the records related to the same 
* symbol go the same reducer but we have to make sure that all the same key data
* go in a single reduce() call */

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
 
public class ActualKeyGroupingComparator extends WritableComparator {
 	protected ActualKeyGroupingComparator() {
		super(CompositeKey.class, true);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
	 	CompositeKey key1 = (CompositeKey) w1;
		CompositeKey key2 = (CompositeKey) w2;
		// (check on symbol)
		return key1.symbol.toString().compareTo(key2.symbol.toString());
	}
}
/*  This class ensures that all the records related to a certain 
*   symbol comes to the same reducer(partition)
*/ 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
 
public class ActualKeyPartitioner extends Partitioner<CompositeKey, Text> {
	HashPartitioner<Text, Text> hashPartitioner = new HashPartitioner<Text, Text>();
	Text newKey = new Text();
	@Override
	public int getPartition(CompositeKey key, Text value, int numReduceTasks) {
		try {
			newKey.set(key.symbol);
			return hashPartitioner.getPartition(newKey, value, numReduceTasks);
		} catch (Exception e) {
			e.printStackTrace();
			return (int) (Math.random() * numReduceTasks);
		}
	}
}
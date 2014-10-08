import java.io.IOException; 
import java.util.Iterator;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer; 
	
public class SortingReducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterator<Text> values, Context context) throws IOException,InterruptedException{ 
			while (values.hasNext()) {
							Text transInfoObj = new Text();
							transInfoObj =(Text) values.next();
							context.write(key, transInfoObj);
			}
	}
}
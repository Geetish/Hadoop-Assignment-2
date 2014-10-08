import java.io.IOException; 
import java.util.Iterator;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer; 
	
public class TraderReducer extends Reducer<CompositeKey, Text, Text, Text> {

	public void reduce(CompositeKey key, Iterator<Text> values, Context context) throws IOException,InterruptedException{ 
						while (values.hasNext()) {
							Text transInfoObj = new Text();
							transInfoObj =(Text) values.next();
							Text outputKeyText = new Text(key.toString());
							Text outputValueText = new Text("");
							context.write(outputKeyText, outputValueText);
						}			
	}
}
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class TraderMapper extends Mapper<LongWritable, Text, CompositeKey, Text>  {

	public void map(LongWritable key, Text value,
		  Context context)
		  throws IOException, InterruptedException {
		String line = value.toString();
		// Check if all the parameters are there
		if(line.indexOf("44=")!=-1 && line.indexOf("54=")!=-1 && line.indexOf("55=")!=-1){
			// get the timeStamp
			String timeStamp = line.substring(0,line.indexOf(" "));
			// get the price
			Float price = Float.parseFloat(line.substring(line.indexOf("=", line.indexOf("44=")+1)+1,line.indexOf("|", line.indexOf("44=")+1)));
			// get the side 
			Integer side = Integer.parseInt(line.substring(line.indexOf("=", line.indexOf("54=")+1)+1,line.indexOf("|", line.indexOf("54=")+1)));
			// get the symbol
			String symbol = line.substring(line.indexOf("=", line.indexOf("55=")+1)+1,line.indexOf("|", line.indexOf("55=")+1));
			CompositeKey transKey = new CompositeKey(symbol,side,price,timeStamp); 
			TransactionInfo transObj = new TransactionInfo(symbol,side,price,timeStamp);
			Text outputText = new Text(transObj.toString());
			context.write(transKey,outputText);
		}
	 }
 }
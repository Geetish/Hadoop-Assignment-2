import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.DataOutput;
import java.io.DataInput;
import org.apache.hadoop.io.Writable;

public class TransactionInfo implements Writable{
	
	Text symbol;
	public int side;
	public float price;
	Text timeStamp;
	
	// Default Constructor
	TransactionInfo(){
		symbol=new Text();
		side =1;
		price = 0.0f;
		timeStamp = new Text();
	}
	
	// Constructor
	TransactionInfo(String sym,int side,float price, String timeStmp){
		symbol = new Text(sym.trim());
		this.side=side;
		this.price = price;
		timeStamp = new Text(timeStmp.trim());
	}
	
	//Write method
	public void write(DataOutput out) throws IOException {
		symbol.write(out);
		out.writeInt(side);
		out.writeFloat(price);
		timeStamp.write(out);
	}
  
  // Read Method
  public void readFields(DataInput in) throws IOException{
		symbol.readFields(in);
		side = in.readInt();
		price = in.readFloat();
		timeStamp.readFields(in);
  }
    
  //override String method
  public String toString(){
	return "";
  }
  
}
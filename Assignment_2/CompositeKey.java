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
import org.apache.hadoop.io.WritableComparable;

public class CompositeKey implements WritableComparable{
	
	Text symbol;
	public int side;
	public float price;
	Text timeStamp;
	
	// Default Constructor
	CompositeKey(){
		symbol=new Text();
		side =1;
		price = 0.0f;
		timeStamp = new Text();
	}
	
	// Constructor
	CompositeKey(String sym,int side,float price, String timeStmp){
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
  /*
  // override equals
  public boolean equals(Object o){
	if(!(o instanceof CompositeKey )){
		return false;
	}
	else{
		CompositeKey c = (CompositeKey) o;
		return this.symbol.equals(c.symbol) && this.side == c.side && this.price == c.price && this.timeStamp.equals(c.timeStamp); 
	}
  }
  */
  // Override CompareTo
  public int compareTo(CompositeKey args0){
	if(this.symbol.equals(args0.symbol)){
		if(this.timeStamp.equals(args0.timeStamp)){
			if(this.side==args0.side){
				if(this.price==args0.price){
					return 0;
				}
				else if(this.price > args0.price) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else if(this.side > args0.side){
				return 1;
			}
			else {
				return -1;
			}
		}
		else{
			return this.timeStamp.toString().compareTo(args0.timeStamp.toString());
		}
	}
	else{
		return this.symbol.toString().compareTo(args0.symbol.toString());
	}
  }
  
  //override String method
  public String toString(){
	return symbol+"::"+timeStamp + "::" + side + "::" + price;
  }
  
}
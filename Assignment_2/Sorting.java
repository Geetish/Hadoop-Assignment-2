import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;


public class Sorting {
 public static void main(String[] args) throws IOException, InterruptedException {
    if (args.length != 2) {
      System.err.println("Usage: MaxTemperature  ");
      System.exit(-1);
    }
	Configuration conf = new Configuration();
    Job job = new Job(conf, "sorter");
	job.setJarByClass(Sorting.class);
	job.setMapperClass(SortingMapper.class);
	job.setReducerClass(SortingReducer.class);
	job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(Text.class);
	job.setNumReduceTasks(1);
	FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	boolean result = job.waitForCompletion(true);
	System.exit(result ? 0 : 1);
  }
}
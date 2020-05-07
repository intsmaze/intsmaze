package cn.intsmaze.hbase.mr;//package cn.intsmaze.hbase.mr;
//
//import java.io.IOException;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.Scan;
//import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
//import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
//import org.apache.hadoop.hbase.mapreduce.TableMapper;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//
//public class HbaseReader {
//
//	public static String t_user_info = "t_user_info";
//
//	static class HdfsSinkMapper extends TableMapper<Text, NullWritable> {
//
//		@Override
//		protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
//
//			byte[] bytes = key.copyBytes();
//			String rowkey = new String(bytes);
//			byte[] usernameBytes = value.getValue("base_info".getBytes(), "username".getBytes());
//			String username = new String(usernameBytes);
//			context.write(new Text(rowkey + "\t" + username), NullWritable.get());
//
//		}
//
//	}
//
//	static class HdfsSinkReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
//
//		@Override
//		protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
//
//			context.write(key, NullWritable.get());
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		Configuration conf = HBaseConfiguration.create();
//		conf.set("hbase.zookeeper.quorum", "mini1:2181,mini2:2181,mini3:2181");
//
//		Job job = Job.getInstance(conf);
//
//		job.setJarByClass(HbaseReader.class);
//
//		// job.setMapperClass(HdfsSinkMapper.class);
//		Scan scan = new Scan();
//		TableMapReduceUtil.initTableMapperJob(t_user_info, scan, HdfsSinkMapper.class, Text.class, NullWritable.class, job);
//		job.setReducerClass(HdfsSinkReducer.class);
//
//		FileOutputFormat.setOutputPath(job, new Path("c:/hbasetest/output"));
//
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(NullWritable.class);
//
//		job.waitForCompletion(true);
//	}
//
//}

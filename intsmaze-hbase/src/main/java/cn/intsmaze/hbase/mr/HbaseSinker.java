package cn.intsmaze.hbase.mr;//package cn.intsmaze.hbase.mr;
//
//import java.io.IOException;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.Mutation;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
////import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
////import org.apache.hadoop.hbase.mapreduce.TableReducer;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Mapper.Context;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//
//public class HbaseSinker {
//
//	public static String flow_fields_import = "flow_fields_import";
//
//	static class HbaseSinkMrMapper extends Mapper<LongWritable, Text, FlowBean, NullWritable> {
//		@Override
//		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//
//			String line = value.toString();
//			String[] fields = line.split("\t");
//			String phone = fields[0];
//			String url = fields[1];
//
//			FlowBean bean = new FlowBean(phone, url);
//
//			context.write(bean, NullWritable.get());
//		}
//	}
//
//	static class HbaseSinkMrReducer extends TableReducer<FlowBean, NullWritable, ImmutableBytesWritable> {
//
//		@Override
//		protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
//
//			Put put = new Put(key.getPhone().getBytes());
//			put.add("f1".getBytes(), "url".getBytes(), key.getUrl().getBytes());
//
//			context.write(new ImmutableBytesWritable(key.getPhone().getBytes()), put);
//
//		}
//
//	}
//
//	public static void main(String[] args) throws Exception {
//		Configuration conf = HBaseConfiguration.create();
//		conf.set("hbase.zookeeper.quorum", "spark01");
//
//		HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
//
//		boolean tableExists = hBaseAdmin.tableExists(flow_fields_import);
//		if (tableExists) {
//			hBaseAdmin.disableTable(flow_fields_import);
//			hBaseAdmin.deleteTable(flow_fields_import);
//		}
//		HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(flow_fields_import));
//		HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("f1".getBytes());
//		desc.addFamily(hColumnDescriptor);
//
//		hBaseAdmin.createTable(desc);
//
//		Job job = Job.getInstance(conf);
//
//		job.setJarByClass(HbaseSinker.class);
//
//		job.setMapperClass(HbaseSinkMrMapper.class);
//		TableMapReduceUtil.initTableReducerJob(flow_fields_import, HbaseSinkMrReducer.class, job);
//
//		FileInputFormat.setInputPaths(job, new Path("c:/hbasetest/data"));
//
//		job.setMapOutputKeyClass(FlowBean.class);
//		job.setMapOutputValueClass(NullWritable.class);
//
//		job.setOutputKeyClass(ImmutableBytesWritable.class);
//		job.setOutputValueClass(Mutation.class);
//
//		job.waitForCompletion(true);
//
//	}
//
//}

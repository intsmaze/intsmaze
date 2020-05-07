package cn.hive.bigdata.hive.inputformat;//package cn.hive.bigdata.hive.inputformat;
//
//import java.io.IOException;
//
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.FileSplit;
//import org.apache.hadoop.mapred.InputSplit;
//import org.apache.hadoop.mapred.JobConf;
//import org.apache.hadoop.mapred.LineRecordReader;
//import org.apache.hadoop.mapred.RecordReader;
//import org.apache.hadoop.mapred.Reporter;
//import org.apache.hadoop.mapred.TextInputFormat;
//
//public class BiDelimiterInputFormat extends TextInputFormat {
//
//	@Override
//	public RecordReader<LongWritable, Text> getRecordReader(
//
//	InputSplit genericSplit, JobConf job, Reporter reporter)
//
//	throws IOException {
//
//		reporter.setStatus(genericSplit.toString());
//
//		MyDemoRecordReader reader = new MyDemoRecordReader(new LineRecordReader(job, (FileSplit) genericSplit));
//
//		// BiRecordReader reader = new BiRecordReader(job, (FileSplit)genericSplit);
//		return reader;
//
//	}
//
//	public static class MyDemoRecordReader implements
//
//	RecordReader<LongWritable, Text> {
//
//		LineRecordReader reader;
//
//		Text text;
//
//		public MyDemoRecordReader(LineRecordReader reader) {
//
//			this.reader = reader;
//
//			text = reader.createValue();
//
//		}
//
//		@Override
//		public void close() throws IOException {
//
//			reader.close();
//
//		}
//
//		@Override
//		public LongWritable createKey() {
//
//			return reader.createKey();
//
//		}
//
//		@Override
//		public Text createValue() {
//
//			return new Text();
//
//		}
//
//		@Override
//		public long getPos() throws IOException {
//
//			return reader.getPos();
//
//		}
//
//		@Override
//		public float getProgress() throws IOException {
//
//			return reader.getProgress();
//
//		}
//
//		@Override
//		public boolean next(LongWritable key, Text value) throws IOException {
//
//			boolean next = reader.next(key, text);
//			if(next){
//				text.toString().replaceAll("\\|\\|", "\\|");
//				Text replaceText = new Text();
//				value.set(replaceText);
//			}
//			return next;
//
//
//
//			/*while (reader.next(key, text)) {
//
//				String strReplace = text.toString().toLowerCase().replaceAll("\\|\\|", "|");
//
//				Text txtReplace = new Text();
//
//				txtReplace.set(strReplace);
//
//				value.set(txtReplace.getBytes(), 0, txtReplace.getLength());
//
//				return true;
//
//			}
//
//			return false;*/
//
//		}
//
//	}
//
//}

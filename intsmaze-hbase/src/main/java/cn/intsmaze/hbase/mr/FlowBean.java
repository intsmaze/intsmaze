package cn.intsmaze.hbase.mr;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class FlowBean implements Writable {

	long upflow;
	long downflow;
	long sumflow;

	public FlowBean() {
	}

	public FlowBean(long upflow, long downflow) {
		super();
		this.upflow = upflow;
		this.downflow = downflow;
		this.sumflow = upflow + downflow;
	}

	public FlowBean(String phone, String url) {
		// TODO Auto-generated constructor stub
	}

	public long getSumflow() {
		return sumflow;
	}

	public void setSumflow(long sumflow) {
		this.sumflow = sumflow;
	}

	public long getUpflow() {
		return upflow;
	}

	public void setUpflow(long upflow) {
		this.upflow = upflow;
	}

	public long getDownflow() {
		return downflow;
	}

	public void setDownflow(long downflow) {
		this.downflow = downflow;
	}

	@Override
    public void write(DataOutput out) throws IOException {

		out.writeLong(upflow);
		out.writeLong(downflow);
		out.writeLong(sumflow);

	}

	@Override
    public void readFields(DataInput in) throws IOException {
		upflow = in.readLong();
		downflow = in.readLong();
		sumflow = in.readLong();

	}

	@Override
	public String toString() {

		return upflow + "\t" + downflow + "\t" + sumflow;
	}

	public Text getPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	public Text getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}

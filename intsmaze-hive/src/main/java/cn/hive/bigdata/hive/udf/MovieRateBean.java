package cn.hive.bigdata.hive.udf;

//{"movie":"1721","rate":"3","timeStamp":"965440048","uid":"5114"}
public class MovieRateBean {

	private String movie;
	private String rate;
	private String timeStamp;
	private String uid;
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return movie + "\t" + rate + "\t" + timeStamp + "\t" + uid;
	}


}

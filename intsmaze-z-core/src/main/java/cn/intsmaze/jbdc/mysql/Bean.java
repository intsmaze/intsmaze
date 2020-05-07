package cn.intsmaze.jbdc.mysql;

public class Bean {
	
	private String name;

	public Bean() {
	}

	public Bean(String n) {
		this.name = n;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

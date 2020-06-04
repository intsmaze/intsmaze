package com.intsmaze.spring.di;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PersonDi {
	
	private Long pid;
	private String name;
	private StudentDi studentDi;
	private List lists;
	private Set sets;
	private Map map;
	private Object[] objects;
	private Properties properties;
	
	
	public PersonDi() {
		System.out.println("new PersonDi");
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public StudentDi getStudentDi() {
		return studentDi;
	}
	public void setStudentDi(StudentDi studentDi) {
		this.studentDi = studentDi;
		System.out.println("set PersonDi");
	}
	public List getLists() {
		return lists;
	}
	public void setLists(List lists) {
		this.lists = lists;
	}
	public Set getSets() {
		return sets;
	}
	public void setSets(Set sets) {
		this.sets = sets;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Object[] getObjects() {
		return objects;
	}
	public void setObjects(Object[] objects) {
		this.objects = objects;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", name=" + name + ", student=" + studentDi
				+ ", lists=" + lists + ", sets=" + sets + ", map=" + map
				+ ", objects=" + Arrays.toString(objects) + ", properties="
				+ properties + "]";
	}
}
